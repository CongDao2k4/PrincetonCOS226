/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[][] matrix;
    private int n;
    private int numberOpensite;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF uf2;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) throw new IllegalArgumentException("n must greater than or equal to 1");
        this.n = n;
        matrix = new boolean[n + 2][n + 1];
        // for(int f=0; f<=n; f++) matrix[0][f] = -1;
        // for(int i=0; i<=n; i++) matrix[i][0] = -1;
        for (int i = 0; i <= n; i++)
            for (int f = 0; f <= n; f++) {
                matrix[i][f] = false;
            }
        uf = new WeightedQuickUnionUF((n + 1) * (n + 1) + 1);
        uf2 = new WeightedQuickUnionUF((n + 1) * (n + 1) + 1);
        matrix[0][0] = true;
        matrix[n + 1][0] = true;
        numberOpensite = 0;
    }

    /******
     * Vấn đề là quá nhiều lần gọi hàm union, find từ UF .
     * Do đó ta cầra cho isFull() hoặc isOpen() ,
     * hoặc có thể rút gọn lại số lần if tại Union() bằng cách kiểm tra
     */
    // Union each square
    private void Union(int row, int col) {
        int left = col - 1, right = col + 1, up = row - 1, down = row + 1;
        if (left >= 1 && isOpen(row, left)) {
            uf.union(row * (n + 1) + col, row * (n + 1) + left);
            uf2.union(row * (n + 1) + col, row * (n + 1) + left);

        }
        if (right <= n && isOpen(row, right)) {
            uf.union(row * (n + 1) + col, row * (n + 1) + right);
            uf2.union(row * (n + 1) + col, row * (n + 1) + right);
        }
        if (up >= 1 && isOpen(up, col)) {
            uf.union(row * (n + 1) + col, up * (n + 1) + col);
            uf2.union(row * (n + 1) + col, up * (n + 1) + col);
        }
        if (down <= n && isOpen(down, col)) {
            uf.union(row * (n + 1) + col, down * (n + 1) + col);
            uf2.union(row * (n + 1) + col, down * (n + 1) + col);
        }
        if (row == 1) {
            uf.union(row * (n + 1) + col, 0);
            uf2.union(row * (n + 1) + col, 0);
        }
        if (row == n) {
            uf2.union(row * (n + 1) + col, (n + 1) * (n + 1));
        }
        /*****
         * Vấn ề với khi row==n, nó sẽ lập tức kết nối với điểm đáy, và một khi điểm đáy kết nối vs điểm đầu(0,0)
         * thì dù điểm (row,col) ở trên màn hình tách biệt với dòng chảy nước từ ầu 0,0 ến đáy n+1,0
         * nhưng vẫn sẽ là Full, bởi vì nước chảy đến đáy là điểm n+1,0 rồi lại chảy ngược lên nó.
         */
        /***
         * Nếu ta có 2 UF , 1 UF để chứa điểm đầu 0,0 và không có điểm cuối n+1,0
         * UF còn lại thì để làm cho việc
         * if (row == n)
         *     uf.union(row*(n+1)+col , (n + 1) * (n + 1));
         * UF đầu tiên để phục vụ truy vấn cho các hàm open, isFull (ngoại trừ percolates() )
         * UF thứ hai dùng để chỉ phục vụ hàm percolates()
         */
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n)
            throw new IllegalArgumentException("row and col must be in range [1, n]");
        if (!isOpen(row, col)) {
            matrix[row][col] = true;
            numberOpensite++;
            Union(row, col);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n)
            throw new IllegalArgumentException("row and col must be in range [1, n]");
        return matrix[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (row <= 0 || row > n || col <= 0 || col > n)
            throw new IllegalArgumentException("row and col must be in range [1, n]");
        return uf.find(row * (n + 1) + col) == uf.find(0);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return numberOpensite;
    }

    // does the system percolate?

    /****
     * Vấn đề nằm ở chỗ Để tính boolean percolates() đã mất n^2 cho vòng for, vậy
     * nên cần 1 ô ở đầu và 1 ô ở cuối  , nhưng nó không thuộc vào Bảng n*n ô đã cho
     * ô ở vị trí hàng 0, vị trí j tùy ý sao cho mọi ô open=true ở hàng 1 kết nối vs nó(đã ở hàng 1 thì nó đã là thẩm thấu)
     * ô ở vị trí cuối là ở hàng dưới cả hàng i=n, tức là mọi ô ở dưới cùng mà có thẩm thấu thì
     * nước sẽ chay xuống ô v trí hàng n+1, j=0; j=0 để đỡ tốn dữ liệu ô nhớ
     * Khi đó ,nếu cần isFull(i,j) = true: ta chỉ cần xem ô ở (i,j) có kết nối với ô (0,j tùy ý hay ko)
     * Khi đó, nếu cần percolates() = true: ta chỉ cần xem (0,j tùy ý) có kết nối vs (n+1,j=0) không
     */
    public boolean percolates() {
        /******
         * Vấn ề với khi row==n, nó sẽ lập tức kết nối với điểm đáy, và một khi điểm đáy kết nối vs điểm đầu(0,0)
         * thì dù điểm (row,col) ở trên màn hình tách biệt với dòng chảy nước từ ầu 0,0 ến đáy n+1,0
         * nhưng vẫn sẽ là Full, bởi vì nước chảy đến đáy là điểm n+1,0 rồi lại chả ngược lên nó.
         */
        // Nên ta cần cho chạy tất cả n điểm ở hàng cuối trong bảng hiển thị tức row = n, col = 1-> n
        // hoặc cách khác là nếu row ==n , hỏi xem nếu điểm n+1,0 chưa Full thì mới union(row*(n+1)+col, (n+1)*(n+1))
        return uf2.find((n + 1) * (n + 1)) == uf2.find(0);
    }

    // test client (optional)
    public static void main(String[] args) {

    }
}
