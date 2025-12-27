<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang = "en">

<head>

<link rel="shortcut icon" href="https://algs4.cs.princeton.edu/favicon.ico">
<link rel="stylesheet"    href="https://algs4.cs.princeton.edu/java.css" type="text/css">

<title>MergeX.java</title>

<meta HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=iso-8859-1">
<meta NAME="AUTHOR" CONTENT="Robert Sedgewick and Kevin Wayne">
<meta NAME="DESCRIPTION" CONTENT="MergeX code in Java">
<meta NAME="TITLE" CONTENT="MergeX code in Java">
<meta NAME="KEYWORDS" CONTENT="MergeX,java,programming,computer science,algorithm,data structure,program,code">
<meta NAME="ROBOTS" CONTENT="INDEX,FOLLOW">

</head>


<body>
<center><h1>MergeX.java</h1></center><p><br>

Below is the syntax highlighted version of <a href = "MergeX.java">MergeX.java</a>
from <a href = "https://algs4.cs.princeton.edu/22mergesort">&#167;2.2 Mergesort</a>.
<p><br>

<!-- Generator: GNU source-highlight 3.1.8
by Lorenzo Bettini
http://www.lorenzobettini.it
http://www.gnu.org/software/src-highlite -->
<pre><tt><span class="comment">/******************************************************************************</span>
<span class="comment"> *  Compilation:  javac MergeX.java</span>
<span class="comment"> *  Execution:    java MergeX &lt; input.txt</span>
<span class="comment"> *  Dependencies: StdOut.java StdIn.java</span>
<span class="comment"> *  Data files:   </span><span class="url">https://algs4.cs.princeton.edu/22mergesort/tiny.txt</span>
<span class="comment"> *                </span><span class="url">https://algs4.cs.princeton.edu/22mergesort/words3.txt</span>
<span class="comment"> *</span>
<span class="comment"> *  Sorts a sequence of strings from standard input using an</span>
<span class="comment"> *  optimized version of mergesort.</span>
<span class="comment"> *</span>
<span class="comment"> *  % more tiny.txt</span>
<span class="comment"> *  S O R T E X A M P L E</span>
<span class="comment"> *</span>
<span class="comment"> *  % java MergeX &lt; tiny.txt</span>
<span class="comment"> *  A E E L M O P R S T X                 [ one string per line ]</span>
<span class="comment"> *</span>
<span class="comment"> *  % more words3.txt</span>
<span class="comment"> *  bed bug dad yes zoo ... all bad yet</span>
<span class="comment"> *</span>
<span class="comment"> *  % java MergeX &lt; words3.txt</span>
<span class="comment"> *  all bad bed bug dad ... yes yet zoo    [ one string per line ]</span>
<span class="comment"> *</span>
<span class="comment"> ******************************************************************************/</span>

<span class="preproc">import</span><span class="normal"> java</span><span class="symbol">.</span><span class="normal">util</span><span class="symbol">.</span><span class="normal">Comparator</span><span class="symbol">;</span>

<span class="comment">/**</span>
<span class="comment"> *  The {</span><span class="type">@code</span><span class="comment"> MergeX} class provides static methods for sorting an</span>
<span class="comment"> *  array using an optimized version of mergesort.</span>
<span class="comment"> *  </span><span class="keyword">&lt;p&gt;</span>
<span class="comment"> *  In the worst case, this implementation takes</span>
<span class="comment"> *  </span><span class="preproc">&amp;Theta;</span><span class="comment">(</span><span class="keyword">&lt;em&gt;</span><span class="comment">n</span><span class="keyword">&lt;/em&gt;</span><span class="comment"> log </span><span class="keyword">&lt;em&gt;</span><span class="comment">n</span><span class="keyword">&lt;/em&gt;</span><span class="comment">) time to sort an array of</span>
<span class="comment"> *  length </span><span class="keyword">&lt;em&gt;</span><span class="comment">n</span><span class="keyword">&lt;/em&gt;</span><span class="comment"> (assuming comparisons take constant time).</span>
<span class="comment"> *  </span><span class="keyword">&lt;p&gt;</span>
<span class="comment"> *  This sorting algorithm is stable.</span>
<span class="comment"> *  It uses </span><span class="preproc">&amp;Theta;</span><span class="comment">(</span><span class="keyword">&lt;em&gt;</span><span class="comment">n</span><span class="keyword">&lt;/em&gt;</span><span class="comment">) extra memory (not including the input array).</span>
<span class="comment"> *  </span><span class="keyword">&lt;p&gt;</span>
<span class="comment"> *  For additional documentation, see</span>
<span class="comment"> *  </span><span class="keyword">&lt;a</span><span class="normal"> </span><span class="type">href</span><span class="symbol">=</span><span class="string">"https://algs4.cs.princeton.edu/22mergesort"</span><span class="keyword">&gt;</span><span class="comment">Section 2.2</span><span class="keyword">&lt;/a&gt;</span><span class="comment"> of</span>
<span class="comment"> *  </span><span class="keyword">&lt;i&gt;</span><span class="comment">Algorithms, 4th Edition</span><span class="keyword">&lt;/i&gt;</span><span class="comment"> by Robert Sedgewick and Kevin Wayne.</span>
<span class="comment"> *</span>
<span class="comment"> *  </span><span class="type">@author</span><span class="comment"> Robert Sedgewick</span>
<span class="comment"> *  </span><span class="type">@author</span><span class="comment"> Kevin Wayne</span>
<span class="comment"> */</span>
<span class="keyword">public</span><span class="normal"> </span><span class="keyword">class</span><span class="normal"> </span><span class="classname">MergeX</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="keyword">final</span><span class="normal"> </span><span class="type">int</span><span class="normal"> CUTOFF </span><span class="symbol">=</span><span class="normal"> </span><span class="number">7</span><span class="symbol">;</span><span class="normal">  </span><span class="comment">// cutoff to insertion sort</span>

<span class="normal">    </span><span class="comment">// This class should not be instantiated.</span>
<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="function">MergeX</span><span class="symbol">()</span><span class="normal"> </span><span class="cbracket">{</span><span class="normal"> </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">merge</span><span class="symbol">(</span><span class="normal">Comparable</span><span class="symbol">[]</span><span class="normal"> src</span><span class="symbol">,</span><span class="normal"> Comparable</span><span class="symbol">[]</span><span class="normal"> dst</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> mid</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> hi</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>

<span class="normal">        </span><span class="comment">// precondition: src[lo .. mid] and src[mid+1 .. hi] are sorted subarrays</span>
<span class="normal">        </span><span class="keyword">assert</span><span class="normal"> </span><span class="function">isSorted</span><span class="symbol">(</span><span class="normal">src</span><span class="symbol">,</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> mid</span><span class="symbol">);</span>
<span class="normal">        </span><span class="keyword">assert</span><span class="normal"> </span><span class="function">isSorted</span><span class="symbol">(</span><span class="normal">src</span><span class="symbol">,</span><span class="normal"> mid</span><span class="symbol">+</span><span class="number">1</span><span class="symbol">,</span><span class="normal"> hi</span><span class="symbol">);</span>

<span class="normal">        </span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> j </span><span class="symbol">=</span><span class="normal"> mid</span><span class="symbol">+</span><span class="number">1</span><span class="symbol">;</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> k </span><span class="symbol">=</span><span class="normal"> lo</span><span class="symbol">;</span><span class="normal"> k </span><span class="symbol">&lt;=</span><span class="normal"> hi</span><span class="symbol">;</span><span class="normal"> k</span><span class="symbol">++)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            </span><span class="keyword">if</span><span class="normal">      </span><span class="symbol">(</span><span class="normal">i </span><span class="symbol">&gt;</span><span class="normal"> mid</span><span class="symbol">)</span><span class="normal">              dst</span><span class="symbol">[</span><span class="normal">k</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> src</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">++];</span>
<span class="normal">            </span><span class="keyword">else</span><span class="normal"> </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">j </span><span class="symbol">&gt;</span><span class="normal"> hi</span><span class="symbol">)</span><span class="normal">               dst</span><span class="symbol">[</span><span class="normal">k</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> src</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">++];</span>
<span class="normal">            </span><span class="keyword">else</span><span class="normal"> </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="function">less</span><span class="symbol">(</span><span class="normal">src</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">],</span><span class="normal"> src</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">]))</span><span class="normal"> dst</span><span class="symbol">[</span><span class="normal">k</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> src</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">++];</span><span class="normal">   </span><span class="comment">// to ensure stability</span>
<span class="normal">            </span><span class="keyword">else</span><span class="normal">                           dst</span><span class="symbol">[</span><span class="normal">k</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> src</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">++];</span>
<span class="normal">        </span><span class="cbracket">}</span>

<span class="normal">        </span><span class="comment">// postcondition: dst[lo .. hi] is sorted subarray</span>
<span class="normal">        </span><span class="keyword">assert</span><span class="normal"> </span><span class="function">isSorted</span><span class="symbol">(</span><span class="normal">dst</span><span class="symbol">,</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> hi</span><span class="symbol">);</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">sort</span><span class="symbol">(</span><span class="normal">Comparable</span><span class="symbol">[]</span><span class="normal"> src</span><span class="symbol">,</span><span class="normal"> Comparable</span><span class="symbol">[]</span><span class="normal"> dst</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> hi</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="comment">// if (hi &lt;= lo) return;</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">hi </span><span class="symbol">&lt;=</span><span class="normal"> lo </span><span class="symbol">+</span><span class="normal"> CUTOFF</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            </span><span class="function">insertionSort</span><span class="symbol">(</span><span class="normal">dst</span><span class="symbol">,</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> hi</span><span class="symbol">);</span>
<span class="normal">            </span><span class="keyword">return</span><span class="symbol">;</span>
<span class="normal">        </span><span class="cbracket">}</span>
<span class="normal">        </span><span class="type">int</span><span class="normal"> mid </span><span class="symbol">=</span><span class="normal"> lo </span><span class="symbol">+</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">hi </span><span class="symbol">-</span><span class="normal"> lo</span><span class="symbol">)</span><span class="normal"> </span><span class="symbol">/</span><span class="normal"> </span><span class="number">2</span><span class="symbol">;</span>
<span class="normal">        </span><span class="function">sort</span><span class="symbol">(</span><span class="normal">dst</span><span class="symbol">,</span><span class="normal"> src</span><span class="symbol">,</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> mid</span><span class="symbol">);</span>
<span class="normal">        </span><span class="function">sort</span><span class="symbol">(</span><span class="normal">dst</span><span class="symbol">,</span><span class="normal"> src</span><span class="symbol">,</span><span class="normal"> mid</span><span class="symbol">+</span><span class="number">1</span><span class="symbol">,</span><span class="normal"> hi</span><span class="symbol">);</span>

<span class="normal">        </span><span class="comment">// if (!less(src[mid+1], src[mid])) {</span>
<span class="normal">        </span><span class="comment">//    for (int i = lo; i &lt;= hi; i++) dst[i] = src[i];</span>
<span class="normal">        </span><span class="comment">//    return;</span>
<span class="normal">        </span><span class="comment">// }</span>

<span class="normal">        </span><span class="comment">// using System.arraycopy() is a bit faster than the above loop</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(!</span><span class="function">less</span><span class="symbol">(</span><span class="normal">src</span><span class="symbol">[</span><span class="normal">mid</span><span class="symbol">+</span><span class="number">1</span><span class="symbol">],</span><span class="normal"> src</span><span class="symbol">[</span><span class="normal">mid</span><span class="symbol">]))</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            System</span><span class="symbol">.</span><span class="function">arraycopy</span><span class="symbol">(</span><span class="normal">src</span><span class="symbol">,</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> dst</span><span class="symbol">,</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> hi </span><span class="symbol">-</span><span class="normal"> lo </span><span class="symbol">+</span><span class="normal"> </span><span class="number">1</span><span class="symbol">);</span>
<span class="normal">            </span><span class="keyword">return</span><span class="symbol">;</span>
<span class="normal">        </span><span class="cbracket">}</span>

<span class="normal">        </span><span class="function">merge</span><span class="symbol">(</span><span class="normal">src</span><span class="symbol">,</span><span class="normal"> dst</span><span class="symbol">,</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> mid</span><span class="symbol">,</span><span class="normal"> hi</span><span class="symbol">);</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Rearranges the array in ascending order, using the natural order.</span>
<span class="comment">     * </span><span class="type">@param</span><span class="comment"> a the array to be sorted</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">sort</span><span class="symbol">(</span><span class="normal">Comparable</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        Comparable</span><span class="symbol">[]</span><span class="normal"> aux </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">.</span><span class="function">clone</span><span class="symbol">();</span>
<span class="normal">        </span><span class="function">sort</span><span class="symbol">(</span><span class="normal">aux</span><span class="symbol">,</span><span class="normal"> a</span><span class="symbol">,</span><span class="normal"> </span><span class="number">0</span><span class="symbol">,</span><span class="normal"> a</span><span class="symbol">.</span><span class="normal">length</span><span class="symbol">-</span><span class="number">1</span><span class="symbol">);</span>
<span class="normal">        </span><span class="keyword">assert</span><span class="normal"> </span><span class="function">isSorted</span><span class="symbol">(</span><span class="normal">a</span><span class="symbol">);</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">// sort from a[lo] to a[hi] using insertion sort</span>
<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">insertionSort</span><span class="symbol">(</span><span class="normal">Comparable</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> hi</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> lo</span><span class="symbol">;</span><span class="normal"> i </span><span class="symbol">&lt;=</span><span class="normal"> hi</span><span class="symbol">;</span><span class="normal"> i</span><span class="symbol">++)</span>
<span class="normal">            </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> j </span><span class="symbol">=</span><span class="normal"> i</span><span class="symbol">;</span><span class="normal"> j </span><span class="symbol">&gt;</span><span class="normal"> lo </span><span class="symbol">&amp;&amp;</span><span class="normal"> </span><span class="function">less</span><span class="symbol">(</span><span class="normal">a</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">],</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">-</span><span class="number">1</span><span class="symbol">]);</span><span class="normal"> j</span><span class="symbol">--)</span>
<span class="normal">                </span><span class="function">exch</span><span class="symbol">(</span><span class="normal">a</span><span class="symbol">,</span><span class="normal"> j</span><span class="symbol">,</span><span class="normal"> j</span><span class="symbol">-</span><span class="number">1</span><span class="symbol">);</span>
<span class="normal">    </span><span class="cbracket">}</span>


<span class="normal">    </span><span class="comment">/*******************************************************************</span>
<span class="comment">     *  Utility methods.</span>
<span class="comment">     *******************************************************************/</span>

<span class="normal">    </span><span class="comment">// exchange a[i] and a[j]</span>
<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">exch</span><span class="symbol">(</span><span class="normal">Object</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> i</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> j</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="usertype">Object</span><span class="normal"> swap </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">];</span>
<span class="normal">        a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">];</span>
<span class="normal">        a</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> swap</span><span class="symbol">;</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">// is a[i] &lt; a[j]?</span>
<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">boolean</span><span class="normal"> </span><span class="function">less</span><span class="symbol">(</span><span class="usertype">Comparable</span><span class="normal"> a</span><span class="symbol">,</span><span class="normal"> </span><span class="usertype">Comparable</span><span class="normal"> b</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> a</span><span class="symbol">.</span><span class="function">compareTo</span><span class="symbol">(</span><span class="normal">b</span><span class="symbol">)</span><span class="normal"> </span><span class="symbol">&lt;</span><span class="normal"> </span><span class="number">0</span><span class="symbol">;</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">// is a[i] &lt; a[j]?</span>
<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">boolean</span><span class="normal"> </span><span class="function">less</span><span class="symbol">(</span><span class="usertype">Object</span><span class="normal"> a</span><span class="symbol">,</span><span class="normal"> </span><span class="usertype">Object</span><span class="normal"> b</span><span class="symbol">,</span><span class="normal"> </span><span class="usertype">Comparator</span><span class="normal"> comparator</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> comparator</span><span class="symbol">.</span><span class="function">compare</span><span class="symbol">(</span><span class="normal">a</span><span class="symbol">,</span><span class="normal"> b</span><span class="symbol">)</span><span class="normal"> </span><span class="symbol">&lt;</span><span class="normal"> </span><span class="number">0</span><span class="symbol">;</span>
<span class="normal">    </span><span class="cbracket">}</span>


<span class="normal">    </span><span class="comment">/*******************************************************************</span>
<span class="comment">     *  Version that takes Comparator as argument.</span>
<span class="comment">     *******************************************************************/</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Rearranges the array in ascending order, using the provided order.</span>
<span class="comment">     *</span>
<span class="comment">     * </span><span class="type">@param</span><span class="comment"> a the array to be sorted</span>
<span class="comment">     * </span><span class="type">@param</span><span class="comment"> comparator the comparator that defines the total order</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">sort</span><span class="symbol">(</span><span class="normal">Object</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">,</span><span class="normal"> </span><span class="usertype">Comparator</span><span class="normal"> comparator</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        Object</span><span class="symbol">[]</span><span class="normal"> aux </span><span class="symbol">=</span><span class="normal"> a</span><span class="symbol">.</span><span class="function">clone</span><span class="symbol">();</span>
<span class="normal">        </span><span class="function">sort</span><span class="symbol">(</span><span class="normal">aux</span><span class="symbol">,</span><span class="normal"> a</span><span class="symbol">,</span><span class="normal"> </span><span class="number">0</span><span class="symbol">,</span><span class="normal"> a</span><span class="symbol">.</span><span class="normal">length</span><span class="symbol">-</span><span class="number">1</span><span class="symbol">,</span><span class="normal"> comparator</span><span class="symbol">);</span>
<span class="normal">        </span><span class="keyword">assert</span><span class="normal"> </span><span class="function">isSorted</span><span class="symbol">(</span><span class="normal">a</span><span class="symbol">,</span><span class="normal"> comparator</span><span class="symbol">);</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">merge</span><span class="symbol">(</span><span class="normal">Object</span><span class="symbol">[]</span><span class="normal"> src</span><span class="symbol">,</span><span class="normal"> Object</span><span class="symbol">[]</span><span class="normal"> dst</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> mid</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> hi</span><span class="symbol">,</span><span class="normal"> </span><span class="usertype">Comparator</span><span class="normal"> comparator</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>

<span class="normal">        </span><span class="comment">// precondition: src[lo .. mid] and src[mid+1 .. hi] are sorted subarrays</span>
<span class="normal">        </span><span class="keyword">assert</span><span class="normal"> </span><span class="function">isSorted</span><span class="symbol">(</span><span class="normal">src</span><span class="symbol">,</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> mid</span><span class="symbol">,</span><span class="normal"> comparator</span><span class="symbol">);</span>
<span class="normal">        </span><span class="keyword">assert</span><span class="normal"> </span><span class="function">isSorted</span><span class="symbol">(</span><span class="normal">src</span><span class="symbol">,</span><span class="normal"> mid</span><span class="symbol">+</span><span class="number">1</span><span class="symbol">,</span><span class="normal"> hi</span><span class="symbol">,</span><span class="normal"> comparator</span><span class="symbol">);</span>

<span class="normal">        </span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> j </span><span class="symbol">=</span><span class="normal"> mid</span><span class="symbol">+</span><span class="number">1</span><span class="symbol">;</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> k </span><span class="symbol">=</span><span class="normal"> lo</span><span class="symbol">;</span><span class="normal"> k </span><span class="symbol">&lt;=</span><span class="normal"> hi</span><span class="symbol">;</span><span class="normal"> k</span><span class="symbol">++)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            </span><span class="keyword">if</span><span class="normal">      </span><span class="symbol">(</span><span class="normal">i </span><span class="symbol">&gt;</span><span class="normal"> mid</span><span class="symbol">)</span><span class="normal">                          dst</span><span class="symbol">[</span><span class="normal">k</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> src</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">++];</span>
<span class="normal">            </span><span class="keyword">else</span><span class="normal"> </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">j </span><span class="symbol">&gt;</span><span class="normal"> hi</span><span class="symbol">)</span><span class="normal">                           dst</span><span class="symbol">[</span><span class="normal">k</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> src</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">++];</span>
<span class="normal">            </span><span class="keyword">else</span><span class="normal"> </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="function">less</span><span class="symbol">(</span><span class="normal">src</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">],</span><span class="normal"> src</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">],</span><span class="normal"> comparator</span><span class="symbol">))</span><span class="normal"> dst</span><span class="symbol">[</span><span class="normal">k</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> src</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">++];</span>
<span class="normal">            </span><span class="keyword">else</span><span class="normal">                                       dst</span><span class="symbol">[</span><span class="normal">k</span><span class="symbol">]</span><span class="normal"> </span><span class="symbol">=</span><span class="normal"> src</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">++];</span>
<span class="normal">        </span><span class="cbracket">}</span>

<span class="normal">        </span><span class="comment">// postcondition: dst[lo .. hi] is sorted subarray</span>
<span class="normal">        </span><span class="keyword">assert</span><span class="normal"> </span><span class="function">isSorted</span><span class="symbol">(</span><span class="normal">dst</span><span class="symbol">,</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> hi</span><span class="symbol">,</span><span class="normal"> comparator</span><span class="symbol">);</span>
<span class="normal">    </span><span class="cbracket">}</span>


<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">sort</span><span class="symbol">(</span><span class="normal">Object</span><span class="symbol">[]</span><span class="normal"> src</span><span class="symbol">,</span><span class="normal"> Object</span><span class="symbol">[]</span><span class="normal"> dst</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> hi</span><span class="symbol">,</span><span class="normal"> </span><span class="usertype">Comparator</span><span class="normal"> comparator</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="comment">// if (hi &lt;= lo) return;</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">hi </span><span class="symbol">&lt;=</span><span class="normal"> lo </span><span class="symbol">+</span><span class="normal"> CUTOFF</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            </span><span class="function">insertionSort</span><span class="symbol">(</span><span class="normal">dst</span><span class="symbol">,</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> hi</span><span class="symbol">,</span><span class="normal"> comparator</span><span class="symbol">);</span>
<span class="normal">            </span><span class="keyword">return</span><span class="symbol">;</span>
<span class="normal">        </span><span class="cbracket">}</span>
<span class="normal">        </span><span class="type">int</span><span class="normal"> mid </span><span class="symbol">=</span><span class="normal"> lo </span><span class="symbol">+</span><span class="normal"> </span><span class="symbol">(</span><span class="normal">hi </span><span class="symbol">-</span><span class="normal"> lo</span><span class="symbol">)</span><span class="normal"> </span><span class="symbol">/</span><span class="normal"> </span><span class="number">2</span><span class="symbol">;</span>
<span class="normal">        </span><span class="function">sort</span><span class="symbol">(</span><span class="normal">dst</span><span class="symbol">,</span><span class="normal"> src</span><span class="symbol">,</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> mid</span><span class="symbol">,</span><span class="normal"> comparator</span><span class="symbol">);</span>
<span class="normal">        </span><span class="function">sort</span><span class="symbol">(</span><span class="normal">dst</span><span class="symbol">,</span><span class="normal"> src</span><span class="symbol">,</span><span class="normal"> mid</span><span class="symbol">+</span><span class="number">1</span><span class="symbol">,</span><span class="normal"> hi</span><span class="symbol">,</span><span class="normal"> comparator</span><span class="symbol">);</span>

<span class="normal">        </span><span class="comment">// using System.arraycopy() is a bit faster than the above loop</span>
<span class="normal">        </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(!</span><span class="function">less</span><span class="symbol">(</span><span class="normal">src</span><span class="symbol">[</span><span class="normal">mid</span><span class="symbol">+</span><span class="number">1</span><span class="symbol">],</span><span class="normal"> src</span><span class="symbol">[</span><span class="normal">mid</span><span class="symbol">],</span><span class="normal"> comparator</span><span class="symbol">))</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            System</span><span class="symbol">.</span><span class="function">arraycopy</span><span class="symbol">(</span><span class="normal">src</span><span class="symbol">,</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> dst</span><span class="symbol">,</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> hi </span><span class="symbol">-</span><span class="normal"> lo </span><span class="symbol">+</span><span class="normal"> </span><span class="number">1</span><span class="symbol">);</span>
<span class="normal">            </span><span class="keyword">return</span><span class="symbol">;</span>
<span class="normal">        </span><span class="cbracket">}</span>

<span class="normal">        </span><span class="function">merge</span><span class="symbol">(</span><span class="normal">src</span><span class="symbol">,</span><span class="normal"> dst</span><span class="symbol">,</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> mid</span><span class="symbol">,</span><span class="normal"> hi</span><span class="symbol">,</span><span class="normal"> comparator</span><span class="symbol">);</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">// sort from a[lo] to a[hi] using insertion sort</span>
<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">insertionSort</span><span class="symbol">(</span><span class="normal">Object</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> hi</span><span class="symbol">,</span><span class="normal"> </span><span class="usertype">Comparator</span><span class="normal"> comparator</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> lo</span><span class="symbol">;</span><span class="normal"> i </span><span class="symbol">&lt;=</span><span class="normal"> hi</span><span class="symbol">;</span><span class="normal"> i</span><span class="symbol">++)</span>
<span class="normal">            </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> j </span><span class="symbol">=</span><span class="normal"> i</span><span class="symbol">;</span><span class="normal"> j </span><span class="symbol">&gt;</span><span class="normal"> lo </span><span class="symbol">&amp;&amp;</span><span class="normal"> </span><span class="function">less</span><span class="symbol">(</span><span class="normal">a</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">],</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">j</span><span class="symbol">-</span><span class="number">1</span><span class="symbol">],</span><span class="normal"> comparator</span><span class="symbol">);</span><span class="normal"> j</span><span class="symbol">--)</span>
<span class="normal">                </span><span class="function">exch</span><span class="symbol">(</span><span class="normal">a</span><span class="symbol">,</span><span class="normal"> j</span><span class="symbol">,</span><span class="normal"> j</span><span class="symbol">-</span><span class="number">1</span><span class="symbol">);</span>
<span class="normal">    </span><span class="cbracket">}</span>


<span class="normal">   </span><span class="comment">/***************************************************************************</span>
<span class="comment">    *  Check if array is sorted - useful for debugging.</span>
<span class="comment">    ***************************************************************************/</span>
<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">boolean</span><span class="normal"> </span><span class="function">isSorted</span><span class="symbol">(</span><span class="normal">Comparable</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> </span><span class="function">isSorted</span><span class="symbol">(</span><span class="normal">a</span><span class="symbol">,</span><span class="normal"> </span><span class="number">0</span><span class="symbol">,</span><span class="normal"> a</span><span class="symbol">.</span><span class="normal">length </span><span class="symbol">-</span><span class="normal"> </span><span class="number">1</span><span class="symbol">);</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">boolean</span><span class="normal"> </span><span class="function">isSorted</span><span class="symbol">(</span><span class="normal">Comparable</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> hi</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> lo </span><span class="symbol">+</span><span class="normal"> </span><span class="number">1</span><span class="symbol">;</span><span class="normal"> i </span><span class="symbol">&lt;=</span><span class="normal"> hi</span><span class="symbol">;</span><span class="normal"> i</span><span class="symbol">++)</span>
<span class="normal">            </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="function">less</span><span class="symbol">(</span><span class="normal">a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">],</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">-</span><span class="number">1</span><span class="symbol">]))</span><span class="normal"> </span><span class="keyword">return</span><span class="normal"> </span><span class="keyword">false</span><span class="symbol">;</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> </span><span class="keyword">true</span><span class="symbol">;</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">boolean</span><span class="normal"> </span><span class="function">isSorted</span><span class="symbol">(</span><span class="normal">Object</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">,</span><span class="normal"> </span><span class="usertype">Comparator</span><span class="normal"> comparator</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> </span><span class="function">isSorted</span><span class="symbol">(</span><span class="normal">a</span><span class="symbol">,</span><span class="normal"> </span><span class="number">0</span><span class="symbol">,</span><span class="normal"> a</span><span class="symbol">.</span><span class="normal">length </span><span class="symbol">-</span><span class="normal"> </span><span class="number">1</span><span class="symbol">,</span><span class="normal"> comparator</span><span class="symbol">);</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">boolean</span><span class="normal"> </span><span class="function">isSorted</span><span class="symbol">(</span><span class="normal">Object</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> lo</span><span class="symbol">,</span><span class="normal"> </span><span class="type">int</span><span class="normal"> hi</span><span class="symbol">,</span><span class="normal"> </span><span class="usertype">Comparator</span><span class="normal"> comparator</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> lo </span><span class="symbol">+</span><span class="normal"> </span><span class="number">1</span><span class="symbol">;</span><span class="normal"> i </span><span class="symbol">&lt;=</span><span class="normal"> hi</span><span class="symbol">;</span><span class="normal"> i</span><span class="symbol">++)</span>
<span class="normal">            </span><span class="keyword">if</span><span class="normal"> </span><span class="symbol">(</span><span class="function">less</span><span class="symbol">(</span><span class="normal">a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">],</span><span class="normal"> a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">-</span><span class="number">1</span><span class="symbol">],</span><span class="normal"> comparator</span><span class="symbol">))</span><span class="normal"> </span><span class="keyword">return</span><span class="normal"> </span><span class="keyword">false</span><span class="symbol">;</span>
<span class="normal">        </span><span class="keyword">return</span><span class="normal"> </span><span class="keyword">true</span><span class="symbol">;</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">// print array to standard output</span>
<span class="normal">    </span><span class="keyword">private</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">show</span><span class="symbol">(</span><span class="normal">Object</span><span class="symbol">[]</span><span class="normal"> a</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        </span><span class="keyword">for</span><span class="normal"> </span><span class="symbol">(</span><span class="type">int</span><span class="normal"> i </span><span class="symbol">=</span><span class="normal"> </span><span class="number">0</span><span class="symbol">;</span><span class="normal"> i </span><span class="symbol">&lt;</span><span class="normal"> a</span><span class="symbol">.</span><span class="normal">length</span><span class="symbol">;</span><span class="normal"> i</span><span class="symbol">++)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">            StdOut</span><span class="symbol">.</span><span class="function">println</span><span class="symbol">(</span><span class="normal">a</span><span class="symbol">[</span><span class="normal">i</span><span class="symbol">]);</span>
<span class="normal">        </span><span class="cbracket">}</span>
<span class="normal">    </span><span class="cbracket">}</span>

<span class="normal">    </span><span class="comment">/**</span>
<span class="comment">     * Reads in a sequence of strings from standard input; mergesorts them</span>
<span class="comment">     * (using an optimized version of mergesort);</span>
<span class="comment">     * and prints them to standard output in ascending order.</span>
<span class="comment">     *</span>
<span class="comment">     * </span><span class="type">@param</span><span class="comment"> args the command-line arguments</span>
<span class="comment">     */</span>
<span class="normal">    </span><span class="keyword">public</span><span class="normal"> </span><span class="keyword">static</span><span class="normal"> </span><span class="type">void</span><span class="normal"> </span><span class="function">main</span><span class="symbol">(</span><span class="normal">String</span><span class="symbol">[]</span><span class="normal"> args</span><span class="symbol">)</span><span class="normal"> </span><span class="cbracket">{</span>
<span class="normal">        String</span><span class="symbol">[]</span><span class="normal"> a </span><span class="symbol">=</span><span class="normal"> StdIn</span><span class="symbol">.</span><span class="function">readAllStrings</span><span class="symbol">();</span>
<span class="normal">        MergeX</span><span class="symbol">.</span><span class="function">sort</span><span class="symbol">(</span><span class="normal">a</span><span class="symbol">);</span>
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
<br>Last updated: Thu Aug 11 09:05:41 EDT 2022.
</small></address>

</html>
