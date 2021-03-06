package premiere_partie;

class Edge
{
   int from;
   int to;
   boolean used;
    Edge(int x, int y)
    {
	this.from = x;
	this.to = y;
	this.used = false;
    }
    
    final int other(int v)
    {
	if (this.from == v) return this.to; else return this.from;
    }


    ////////////////////////////////////////////// methode ajouter ////////////////////////////////

    public String toString(){
        return "( " + this.from + ", " + this.to + ", " + this.used + " )";
    }


    public void setUsed(boolean used) {
        this.used = used;
    }

    protected Edge clone(){
     Edge clone= new Edge(this.from,this.to);
     clone.used=this.used;
     return clone;
    }

    public boolean egale(Edge edge){
        return ((edge.from==this.from&&edge.to==this.to)
                ||(edge.from==this.to&&edge.to==this.from));
    }

}
