

import java.util.*;

import java.io.*;

/**
 * template
 */
public class template {

    static class FastIO extends PrintWriter {
		private InputStream stream;
		private byte[] buf = new byte[1 << 16];
		private int curChar, numChars;

		// standard input
		public FastIO() { this(System.in, System.out); }
		public FastIO(InputStream i, OutputStream o) {
			super(o);
			stream = i;
		}
		// file input
		public FastIO(String i, String o) throws IOException {
			super(new FileWriter(o));
			stream = new FileInputStream(i);
		}

		// throws InputMismatchException() if previously detected end of file
		private int nextByte() {
			if (numChars == -1) throw new InputMismatchException();
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = stream.read(buf);
				} catch (IOException e) { throw new InputMismatchException(); }
				if (numChars == -1) return -1;  // end of file
			}
			return buf[curChar++];
		}

		// to read in entire lines, replace c <= ' '
		// with a function that checks whether c is a line break
		public String next() {
			int c;
			do { c = nextByte(); } while (c <= ' ');
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = nextByte();
			} while (c > ' ');
			return res.toString();
		}
		public int nextInt() {  // nextLong() would be implemented similarly
			int c;
			do { c = nextByte(); } while (c <= ' ');
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = nextByte();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9') throw new InputMismatchException();
				res = 10 * res + c - '0';
				c = nextByte();
			} while (c > ' ');
			return res * sgn;
		}
		public double nextDouble() { return Double.parseDouble(next()); }
	}
public static  long MOD= 1000000007;

public static  long  gcd(long a, long b) {if (b > a) {return gcd(b, a);} if (b == 0) {return a;} return gcd(b, a % b);}
public static  long expo(long a, long b, long mod) {long res = 1; while (b > 0) {if ((b & 1)==1) res = (res * a) % mod; a = (a * a) % mod; b = b >> 1;} return res;}
public static  long mminvprime(long a, long b) {return expo(a, b - 2, b);}
public static long mod_add(long a, long b, long m) {a = a % m; b = b % m; return (((a + b) % m) + m) % m;}
public static long mod_mul(long a, long b, long m) {a = a % m; b = b % m; return (((a * b) % m) + m) % m;}
public static long mod_sub(long a, long b, long m) {a = a % m; b = b % m; return (((a - b) % m) + m) % m;}
public static long mod_div(long a, long b, long m) {a = a % m; b = b % m; return (mod_mul(a, mminvprime(b, m), m) + m) % m;}  //only for prime m
static class Pair {
    long x;
    long y;
    Pair(long x,long y){
        this.x=x;
        this.y=y;
    }
    
}
public static void sort(Pair[] arr) {
    Comparator<Pair> comparator = new Comparator<>() {
        @Override
        public int compare(Pair p1, Pair p2) {
            return (int)(p1.x
                    - p2.x); // To compare the first element
                            // just
                            // change the variable from p1.y
                            // - p2.y to p1.x-p2.x.
        }
    };
    Arrays.sort(arr, comparator);
    
}
public static long fact[];
public static long invfact[];

public static void calcfac(long n,long mod){
    fact=new long[(int)(n+1)];
    invfact=new long[(int)(n+1)];
    fact[0]=1;
    invfact[0]=1;
    for(int i=1;i<=n;i++){
        fact[i]=(fact[i-1]*i)%mod;
    }
    invfact[(int)n]=mminvprime(fact[(int)n], mod)%mod;
    for(int i=(int)(n-1);i>=1;i--){
        invfact[i]=( invfact[i+1]*(i+1) )%mod;
    }
}
}