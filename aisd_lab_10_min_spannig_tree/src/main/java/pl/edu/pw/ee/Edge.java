package pl.edu.pw.ee;

public class Edge implements Comparable<Edge>{
    private int value;
    private char startName;
    private char endName;
    
    public Edge(int value, char startName, char endName){
        this.value = value;
        this.startName = startName;
        this.endName = endName;
    }
    
    public int getValue(){
        return value;
    }
    
    public char getStartName(){
        return startName;
    }
    
        public char getEndName(){
        return endName;
    }
    

    @Override
    public int compareTo(Edge o) {
        if(this.value==o.getValue()){
            return 0;
        } else if(this.value>=o.getValue()){
            return 1;
        }
        
        return -1;
    }
}
