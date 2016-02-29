public class Matrix{
	private int rows;
	private int columns;
	private int [][]matrix;
	public Matrix(int rows,int columns){
		this.rows=rows;
		this.columns=columns;
		this.matrix = new int[rows][columns];
	};

	public int getValueAt(int row, int column){
		return this.matrix[row][column];
	};

	public void populateMatrix(int []values){
		int counter=0;
		for (int i=0;i<this.rows;i++) {
			for (int j=0;j<this.columns;j++ ) {
				this.matrix[i][j]=values[counter];
				counter++;
			}
		}
	};

	public Matrix addMatrices(Matrix matrix){
    Matrix result = new Matrix(this.rows,this.columns);
		for(int i=0;i<this.rows;i++){
			for (int j=0;j<this.columns;j++ ) {
				result.matrix[i][j]=(this.getValueAt(i,j)+matrix.getValueAt(i,j));
			}
		}
    return result;
	};

	public Matrix multiplyMatrices(Matrix matrix){
    Matrix result = new Matrix(this.rows,matrix.columns);
		for (int i=0;i<this.rows;i++) { 
            for (int j=0;j<matrix.columns;j++) { 
                for (int k=0;k<this.columns;k++) { 
                    result.matrix[i][j] += this.getValueAt(i,k) * matrix.getValueAt(k,j);
                }
            }
        }
        return result;
	};

    public String getStringRepresentation(Matrix matrix){
        String result="";
        for (int i=0;i<matrix.rows ;i++ ) {
            for (int j=0;j<matrix.columns ;j++ ) {
                result+=matrix.getValueAt(i,j)+" ";
            }
            result+="\n";
        }
        return result;
    }


	public int getDeterminant(){
    int total=0; 
    int sign;
    if(this.matrix.length==1){ 
      return(this.matrix[0][0]);
    }
    for(int i=0;i<this.matrix.length;i++){ 
    	Matrix newMatrix = new Matrix(this.matrix.length-1,this.matrix.length-1);
    	for(int j=1;j<this.matrix.length;j++){
    		for(int k=0;k<this.matrix.length;k++){
    			if(k<i){
    				newMatrix.matrix[j-1][k]=this.matrix[j][k];
    			}
    			else if(k>i){
    				newMatrix.matrix[j-1][k-1]=this.matrix[j][k];
    			}
    		}
    	}
    	if(i%2==0)
    		sign=1;
      	else
        	sign=-1;
      	
      	total += sign * (this.matrix[0][i]) * (newMatrix.getDeterminant());
      }
      return(total); 
  };

};