<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang = "en">

<head>

<link rel="shortcut icon" href="https://algs4.cs.princeton.edu/favicon.ico">
<link rel="stylesheet"    href="https://algs4.cs.princeton.edu/java.css" type="text/css">

<title>InsertionX.java</title>

<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=iso-8859-1">
<meta NAME="AUTHOR" CONTENT="Robert Sedgewick and Kevin Wayne">
<meta NAME="DESCRIPTION" CONTENT="InsertionX code in Java">
<meta NAME="TITLE" CONTENT="InsertionX code in Java">
<meta NAME="KEYWORDS" CONTENT="InsertionX,java,programming,computer science,algorithm,data structure,program,code">
<meta NAME="ROBOTS" CONTENT="INDEX,FOLLOW">

</head>


<body>
<center><h1>InsertionX.java</h1></center><p><br>

Below is the syntax highlighted version of <a href = "InsertionX.java">InsertionX.java</a>
from <a href = "https://algs4.cs.princeton.edu/21elementary">&#167;2.1 Elementary Sorts</a>.
<p><br>

<!-- Generator: GNU source-highlight 3.1.8
by Lorenzo Bettini
http://www.lorenzobettini.it
http://www.gnu.org/software/src-highlite -->
<pre><tt><span class="comment">/******************************************************************************</span>
<span class="comment"> *  Compilation:  javac InsertionX.java</span>
<span class="comment"> *  Execution:    java InsertionX &lt; input.txt</span>
<span class="comment"> *  Dependencies: StdOut.java StdIn.java</span>
<span class="comment"> *  Data files:   </span><span class="url">https://algs4.cs.princeton.edu/21elementary/tiny.txt</span>
<span class="comment"> *                </span><span class="url">https://algs4.cs.princeton.edu/21elementary/words3.txt</span>
<span class="comment"> *</span>
<span class="comment"> *  Sorts a sequence of strings from standard input using an optimized</span>
<span class="comment"> *  version of insertion sort that uses half exchanges instead of</span>
<span class="comment"> *  full exchanges to reduce data movement..</span>
<span class="comment"> *</span>
<span class="comment"> *  % more tiny.txt</span>
<span class="comment"> *  S O R T E X A M P L E</span>
<span class="comment"> *</span>
<span class="comment"> *  % java InsertionX &lt; tiny.txt</span>
<span class="comment"> *  A E E L M O P R S T X                 [ one string per line ]</span>
<span class="comment"> *</span>
<span class="comment"> *  % more words3.txt</span>
<span class="comment"> *  bed bug dad yes zoo ... all bad yet</span>
<span class="comment"> *</span>
<span class="comment"> *  % java InsertionX &lt; words3.txt</span>
<span class="comment"> *  all bad bed bug dad ... yes yet zoo   [ one string per line ]</span>
<span class="comment"> *</span>
<span class="comment"> ******************************************************************************/</span>
<span class="comment">/**</span>
<span class="comment"> *  The {</span><span class="type">@code</span><span class="comment"> InsertionX} class provides static methods for sorting</span>
<span class="comment"> *  an array using an optimized version of insertion sort (with half exchanges</span>
<span class="comment"> *  and a sentinel).</span>
<span class="comment"> *  </span><span class="keyword">&lt;p&gt;</span>
<span class="comment"> *  In the worst case, this implementation makes ~ 1/2 </span><span class="keyword">&lt;em&gt;</span><span class="comment">n</span><span class="keyword">&lt;/em&gt;&lt;sup&gt;</span><span class="comment">2</span><span class="keyword">&lt;/sup&gt;</span>
<span class="comment"> *  compares to sort an array of length </span><span class="keyword">&lt;em&gt;</span><span class="comment">n</span><span class="keyword">&lt;/em&gt;</span><span class="comment">.</span>
<span class="comment"> *  So, it is not suitable for sorting large arrays</span>
<span class="comment"> *  (unless the number of inversions is small).</span>
<span class="comment"> *  </span><span class="keyword">&lt;p&gt;</span>
<span class="comment"> *  This sorting algorithm is stable.</span>
<span class="comment"> *  It uses </span><span class="preproc">&amp;Theta;</span><span class="comment">(1) extra memory (not including the input array).</span>
<span class="comment"> *  </span><span class="keyword">&lt;p&gt;</span>
<span class="comment"> *  For additional documentation, see</span>
<span class="comment"> *  </span><span class="keyword">&lt;a</span><span class="normal"> </span><span class="type">href</span><span class="symbol">=</span><span class="string">"https://algs4.cs.princeton.edu/21elementary"</span><span class="keyword">&gt;</span><span class="comment">Section 2.1</span><span class="keyword">&lt;/a&gt;</span><span class="comment"> of</span>
<span class="comment"> *  </span><span class="keyword">&lt;i&gt;</span><span class="comment">Algorithms, 4th Edition</span><span class="keyword">&lt;/i&gt;</span><span class="comment"> by Robert Sedgewick and Kevin Wayne.</span>
<span class="comment"> *</span>
<span class="comment"> *  </span><span class="type">@author</span><span class="comment"> Robert Sedgewick</span>
<span class="comment"> *  </span><span class="type">@author</span><span class="comment"> Kevin Wayne</span>
<span class="comment"> */</span>

<span class="keyword">public</span><span class="normal"> </span><span class="keyword">class</span><span class="normal"> </span><span class="classname">InsertionX</span><span class="normal"> </span><span class="cbracket">{</span>

<span class="normal">    </span><span class="comment">// This class should not be instantiated.</span>
<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="function">InsertionX</span><span class="symbol">()</span><span class="normal"> </span><span class="cbracket">{</span><span class="normal"> </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Rearranges the array in ascending order, using the natural order.</span>
<span class="comment">     * </span><span class="type">@param</span><span class="comment"> a the array to be sorted</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">sort</span><span class="symbol">(</span><span class="normal">Comparable</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="type">int</span><span class="normal"> n </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">.</span><span class="normal">length</span><span class="symbol">;</span>

<span class="normal">        </span><span class="comment">// put smallest element in position to serve as sentinel</span>
<span class="normal">        </span><span class="type">int</span><span class="normal"> exchanges </span><span class="symbol">=</span><span class="normal"> </span><span class="number">0</span><span class="symbol">;</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> n</span><span class="symbol">-</span><span class="number">1</span><span class="symbol">;</span><span class="normal"> i </span><span class="symbol">&gt;</span><span class="normal"> </span><span class="number">0</span><span class="symbol">;</span><span class="normal"> i</span><span class="symbol">--)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="function">less</span><span class="symbol">(</span><span class="normal">a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">],</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">-</span><span class="number">1</span><span class="symbol">]))</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">                </span><span class="function">exch</span><span class="symbol">(</span><span class="normal">a</span><span class="symbol">,</span><span class="normal"> i</span><span class="symbol">,</span><span class="normal"> i</span><span class="symbol">-</span><span class="number">1</span><span class="symbol">);</span>
<span class="normal">                exchanges</span><span class="symbol">++;</span>
<span class="normal">            </span><span class="cbracket">}</span>
<span class="normal">        </span><span class="cbracket">}</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">exchanges </span><span class="symbol">==</span><span class="normal"> </span><span class="number">0</span><span class="symbol">)</span><span class="normal"> </span><span class="keyword">return</span><span class="symbol">;</span>


<span class="normal">        </span><span class="comment">// insertion sort with half-exchanges</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> </span><span class="number">2</span><span class="symbol">;</span><span class="normal"> i </span><span class="symbol">&lt;</span><span class="normal"> n</span><span class="symbol">;</span><span class="normal"> i</span><span class="symbol">++)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            </span><span class="usertype">Comparable</span><span class="normal"> v </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">];</span>
<span class="normal">            </span><span class="type">int</span><span class="normal"> j </span><span class="symbol">=</span><span class="normal"> i</span><span class="symbol">;</span>
<span class="normal">            </span><span class="keyword">while</span><span class="normal"> </span><span class="symbol">(</span><span class="function">less</span><span class="symbol">(</span><span class="normal">v</span><span class="symbol">,</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">-</span><span class="number">1</span><span class="symbol">]))</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">                a</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">-</span><span class="number">1</span><span class="symbol">];</span>
<span class="normal">                j</span><span class="symbol">--;</span>
<span class="normal">            </span><span class="cbracket">}</span>
<span class="normal">            a</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> v</span><span class="symbol">;</span>
<span class="normal">        </span><span class="cbracket">}</span>

<span class="normal">        </span><span class="keyword">assert</span><span class="normal"> </span><span class="function">isSorted</span><span class="symbol">(</span><span class="normal">a</span><span class="symbol">);</span>
<span class="normal">    </span><span class="cbracket">}</span>


<span class="normal">   </span><span class="comment">/***************************************************************************</span>
<span class="comment">    *  Helper sorting functions.</span>
<span class="comment">    ***************************************************************************/</span>

<span class="normal">    </span><span class="comment">// is v &lt; w ?</span>
<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">boolean</span><span class="normal"> </span><span class="function">less</span><span class="symbol">(</span><span class="usertype">Comparable</span><span class="normal"> v</span><span class="symbol">,</span><span class="normal"> </span><span class="usertype">Comparable</span><span class="normal"> w</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> v</span><span class="symbol">.</span><span class="function">compareTo</span><span class="symbol">(</span><span class="normal">w</span><span class="symbol">)</span><span class="normal"> </span><span class="symbol">&lt;</span><span class="normal"> </span><span class="number">0</span><span class="symbol">;</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">// exchange a[i] and a[j]</span>
<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">exch</span><span class="symbol">(</span><span class="normal">Object</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> i</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> j</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="usertype">Object</span><span class="normal"> swap </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">];</span>
<span class="normal">        a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">];</span>
<span class="normal">        a</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> swap</span><span class="symbol">;</span>
<span class="normal">    </span><span class="cbracket">}</span>


<span class="normal">   </span><span class="comment">/***************************************************************************</span>
<span class="comment">    *  Check if array is sorted - useful for debugging.</span>
<span class="comment">    ***************************************************************************/</span>
<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">boolean</span><span class="normal"> </span><span class="function">isSorted</span><span class="symbol">(</span><span class="normal">Comparable</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> </span><span class="number">1</span><span class="symbol">;</span><span class="normal"> i </span><span class="symbol">&lt;</span><span class="normal"> a</span><span class="symbol">.</span><span class="normal">length</span><span class="symbol">;</span><span class="normal"> i</span><span class="symbol">++)</span>
<span class="normal">            </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="function">less</span><span class="symbol">(</span><span class="normal">a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">],</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">-</span><span class="number">1</span><span class="symbol">]))</span><span class="normal"> </span><span class="keyword">return</span><span class="normal"> </span><span class="keyword">false</span><span class="symbol">;</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> </span><span class="keyword">true</span><span class="symbol">;</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">// print array to standard output</span>
<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">show</span><span class="symbol">(</span><span class="normal">Comparable</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> </span><span class="number">0</span><span class="symbol">;</span><span class="normal"> i </span><span class="symbol">&lt;</span><span class="normal"> a</span><span class="symbol">.</span><span class="normal">length</span><span class="symbol">;</span><span class="normal"> i</span><span class="symbol">++)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            StdOut</span><span class="symbol">.</span><span class="function">println</span><span class="symbol">(</span><span class="normal">a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">]);</span>
<span class="normal">        </span><span class="cbracket">}</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Reads in a sequence of strings from standard input; insertion sorts them;</span>
<span class="comment">     * and prints them to standard output in ascending order.</span>
<span class="comment">     *</span>
<span class="comment">     * </span><span class="type">@param</span><span class="comment"> args the command-line arguments</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">main</span><span class="symbol">(</span><span class="normal">String</span><span class="symbol">[]</span><span class="normal"> args</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        String</span><span class="symbol">[]</span><span class="normal"> a </span><span class="symbol">=</span><span class="normal"> StdIn</span><span class="symbol">.</span><span class="function">readAllStrings</span><span class="symbol">();</span>
<span class="normal">        InsertionX</span><span class="symbol">.</span><span class="function">sort</span><span class="symbol">(</span><span class="normal">a</span><span class="symbol">);</span>
<span class="normal">        </span><span class="function">show</span><span class="symbol">(</span><span class="normal">a</span><span class="symbol">);</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="cbracket">}</span>
</tt></pre>

<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-10811519-2");
pageTracker._trackPageview();
} catch(err) {}</script>

</body>

<p><br><address><small>
Copyright &copy; 2000&ndash;2019, Robert Sedgewick and Kevin Wayne.
<br>Last updated: Thu Aug 11 09:04:20 EDT 2022.
</small></address>

</html>
