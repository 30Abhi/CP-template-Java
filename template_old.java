import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class template_old {

       static class FastReader {
        BufferedReader br;
        StringTokenizer st;
    
        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
    
        String next() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }
    
        int nextInt() {
            return Integer.parseInt(next());
        }
    
        long nextLong() {
            return Long.parseLong(next());
        }
    
        double nextDouble() {
            return Double.parseDouble(next());
        }
    
        String nextLine() {
            String str = "";
            try {
                str = br.readLine().trim();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return str;
        }
    }
    
    static class FastWriter {
        private final BufferedWriter bw;
    
        public FastWriter() {
            this.bw = new BufferedWriter(new OutputStreamWriter(System.out));
        }
    
        public void print(Object object) throws IOException {
            bw.append("" + object);
        }
    
        public void println(Object object) throws IOException {
            print(object);
            bw.append("\n");
        }
    
        public void close() throws IOException {
            bw.close();
        }
    }

    static class Hash {
        private long[] hash1, hash2, power1, power2;
        private static final long MOD = (long) 1e9 + 7;
        private static final int BASE1 = 5689;
        private static final int BASE2 = 8861;

        public Hash(String s) {
            int n = s.length();
            hash1 = new long[n + 1];
            hash2 = new long[n + 1];
            power1 = new long[n + 1];
            power2 = new long[n + 1];

            power1[0] = 1;
            power2[0] = 1;

            for (int i = 0; i < n; i++) {
                char ch = s.charAt(i);
                hash1[i + 1] = (hash1[i] * BASE1 + (ch - 'a' + 1)) % MOD;
                hash2[i + 1] = (hash2[i] * BASE2 + (ch - 'a' + 1)) % MOD;

                power1[i + 1] = (power1[i] * BASE1) % MOD;
                power2[i + 1] = (power2[i] * BASE2) % MOD;
            }
        }

        public long[] get(int l, int r) {

            l++;
            r++;

            long h1 = (hash1[r] - (hash1[l - 1] * power1[r - l + 1]) % MOD + MOD) % MOD;
            long h2 = (hash2[r] - (hash2[l - 1] * power2[r - l + 1]) % MOD + MOD) % MOD;
            
            return new long[]{h1, h2};

        }
    }

    public static  long MOD= (long)(1e9+7);
    
    public static  long  gcd(long a, long b) {if (b > a) {return gcd(b, a);} if (b == 0) {return a;} return gcd(b, a % b);}
    public static  long expo(long a, long b, long mod) {long res = 1; while (b > 0) {if ((b & 1)==1) res = (res * a) % mod; a = (a * a) % mod; b = b >> 1;} return res;}
    public static  long mminvprime(long a, long b) {return expo(a, b - 2, b);}
    public static long mod_add(long a, long b, long m) {a = a % m; b = b % m; return (((a + b) % m) + m) % m;}
    public static long mod_mul(long a, long b, long m) {a = a % m; b = b % m; return (((a * b) % m) + m) % m;}
    public static long mod_sub(long a, long b, long m) {a = a % m; b = b % m; return (((a - b) % m) + m) % m;}
    public static long mod_div(long a, long b, long m) {a = a % m; b = b % m; return (mod_mul(a, mminvprime(b, m), m) + m) % m;}  //only for prime m
    static class Pair {
        long x, y;
        Pair(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Pair)) return false;
            Pair pair = (Pair) o;
            return x == pair.x && y == pair.y;
        }

        @Override
        public int hashCode() {
            return (int)(31 * x + y);
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
    
    public static int[][]kthparent;
    public static int[]depthofnode;
    public static ArrayList<ArrayList<Integer>>tree;

    public static void createparenttable(int n,int Log){
        kthparent=new int[n+1][Log];
        depthofnode=new int[n+1];

        dfs(1,0,kthparent);;

        for(int i=1;i<Log;i++){
            for(int node=1;node<=n;node++){
                int mid=kthparent[node][i-1];
                kthparent[node][i]=kthparent[mid][i-1];
            }
        }

    }

    public static void dfs(int node,int parent, int[][]kthparent){
        kthparent[node][0]=parent;//2^0==1 st parent
        depthofnode[node]=depthofnode[parent]+1;
        for(int child:tree.get(node)){
            if(child==parent){
                continue;
            }
            dfs(child, node, kthparent);
        }

    }
    // return kth ancestor

    public static int getKthancestor(int node,int k,int Log){

        int u=node;// this u will jump to parent of node

        for(int j=0;j<Log;j++){
            if(((k>>j)&1)!=0){
                u=kthparent[u][j]; // if jth bit of k is set then move to that node
            }
            
        }
        return u;// return the node as it is the kthancestor

    }

    

    public static int LCA(int u,int v,int Log){
        if(depthofnode[u]<depthofnode[v]){
            int temp=u;
            u=v;
            v=temp;
        }

        if(depthofnode[u]>depthofnode[v]){
            u=getKthancestor(u,depthofnode[u]-depthofnode[v],Log);
        }

        if(u==v){
            return u;
        }

        for(int j=Log-1;j>=0;j--){
            if(kthparent[u][j]!=kthparent[v][j]){
                u=kthparent[u][j];
                v=kthparent[v][j];
            }
        }

        return kthparent[u][0];

    }

    public static class DisjointSet{
        int[]parent;
        int[]size;
        DisjointSet(int n){
            
            parent=new int[n+1];
            size=new int[n+1];
            for(int i=0;i<=n;i++){
                parent[i]=i;
                size[i]=1;
            }
            
        }

        int findpar(int v){
            if(parent[v]==v){
                return v;
            }

            return parent[v]=findpar(parent[v]); // Path compression
        }

        boolean isSameSet(int i,int j){
            return findpar(i)==findpar(j);
        }

        void union(int i,int j){

            int a=findpar(i);// root of i
            int b=findpar(j);// root of j

            if(a!=b){
                if(size[a]<size[b]){
                    parent[a]=b;
                    size[b]+=size[a];
    
                }
              
    
                else{
                    parent[b]=a;
                    size[a]+=size[b];
                }
            }
            

        }


    }

    
    public static int[]knightX={ -2, -2, -1, -1, 1, 1, 2, 2 };
    public static int[]knightY= { -1, 1, -2, 2, -2, 2, -1, 1 };

    public static boolean toposort(int node,ArrayList<ArrayList<Integer>>adj,ArrayList<Integer>toposort,int[]vis){
        
        vis[node]=1;

        for(int child:adj.get(node)){

            if(vis[child]==0){
                boolean check=toposort(child,adj,toposort,vis);
                if(!check){
                    return false;
                }
            }

            else if(vis[child]==1){
                return false;
            }

        }

        vis[node]=2;
        toposort.add(node);
        return true;

    }
 
    public static ArrayList<Integer> returntoposort(int indexing,int n,ArrayList<ArrayList<Integer>>adj){
 
        int[]vis=new int[n+1];
 
        ArrayList<Integer>toposort=new ArrayList<>();
    
        for(int i=indexing;i<=n;i++){
            if(vis[i]==0){
                boolean check=toposort(i,adj,toposort,vis);
                if(!check){
                    return new ArrayList<>();
                }
            }
        }
 
        return toposort;
 
    }

    public static int[]sgt=new int[4*100000]; // segment tree 

    public static void build(int st,int end,int idx,int[]arr){
        if(st==end){
            sgt[idx]=arr[st];
            return ;
        }
        int mid=(st+end)/2;
        //left 
        build(st,mid,2*idx,arr);
        //right
        build(mid+1,end,2*idx+1,arr);

        // vlaue of currnode
        sgt[idx]=sgt[2*idx]+sgt[2*idx+1];

    }

    public static void update(int st,int end,int idx,int update_idx,int update_val){

        if(st==end && st==update_idx){
            sgt[idx]=update_val;
            return ;
        }
        int mid=(st+end)/2;
        // left call to update
        update(st,mid,2*idx,update_idx,update_val);
        //right call to update
        update(mid+1,end,2*idx+1,update_idx,update_val);

        sgt[idx]=sgt[2*idx]+sgt[2*idx+1];

    }

    public static int query(int st,int end,int idx,int query_st,int query_end){
        // no overlap
        if(query_st>end || query_end<st){
            return 0;
        }

        // fully overlap
        if(st>=query_st && end<=query_end){
            return sgt[idx]; 
        }

        // partially overlap
        int mid=(st+end)/2;
        int left_cont=query(st, mid, 2*idx, query_st, query_end);
        int right_cont=query(mid+1, end, 2*idx, query_st, query_end);

        return left_cont+right_cont;

    }
    
    
}
