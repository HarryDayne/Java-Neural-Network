package Neural_Network;
public class Matrix 
{
	public double[][] Multiply(double a[][],double b[][])
	{
	    double r[][]= {{0.0,0.0},{0.0,0.0}};//Default array to return if multiplication is not possible
		
		 //Checking if multiplication is possible
	    if(a[0].length!=b.length)
	    {
	    	System.out.println("Multiplication is not possible");
	    	return r;//Should find a way to terminate the flow here without having to return
	    }
	    //Initializing the array that will store the answer
	    double M[][]=new double[a.length][b[0].length];
	    //Multiplying the matrices
	    for (int i = 0; i < a.length; i++) 
	    { 
            for (int j = 0; j < b[0].length; j++)
            { 
                for (int k = 0; k < b.length; k++) 
                    M[i][j] += a[i][k] * b[k][j]; 
            } 
        }
	    //Returning the final answer
	    return M;
	}
	public  void DisplayArray(double a[][])
	{
	  for(int x=0;x<a.length;x++)
	   {
		   for(int y=0;y<a[0].length;y++)
		   {
			  System.out.print(a[x][y]+"  ");
		   }
		   System.out.println();
	   }
	}
	public double[][] ScalarMultiply(double a[][],double b)//Function to multiply a scalar quantity 
	{
		for(int x=0;x<a.length;x++)
		   {
			   for(int y=0;y<a[0].length;y++)
			   {
				  a[x][y]=b*a[x][y];
			   }
		   }
		return a;
	}
	public double[][] SAMultiply(double a[][],double b[][])//Function to multiply each element of an array to another
	{
		for(int x=0;x<a.length;x++)
		   {
			   for(int y=0;y<a[0].length;y++)
			   {
				  a[x][y]=b[x][y]*a[x][y];
			   }
		   }
		return a;
	}
	public double[][] MapFunction(double a[][],String b)//Function to apply a given function on each of the elements for Activation
	{
		if(b.equalsIgnoreCase("Sigmoid"))
		{
			for(int x=0;x<a.length;x++)
			   {
				   for(int y=0;y<a[0].length;y++)
				   {
					  a[x][y]=1/(1+(Math.exp(-a[x][y])));
				   }
			   }
			return a;
		}//Can add other cases for different activation functions
		else if(b.equalsIgnoreCase("DSigmoid"))
		{
			for(int x=0;x<a.length;x++)
			   {
				   for(int y=0;y<a[0].length;y++)
				   {
					  a[x][y]=a[x][y]*(1-a[x][y]);
				   }
			   }
			return a;
		}
		else
		{
			System.out.println("No such activation");
			return a;
		}
	}
	public double[][] Randomize(double a[][])//Function to fill the array with random values
	{
		for(int x=0;x<a.length;x++)
		   {
			   for(int y=0;y<a[0].length;y++)
			   {
				  a[x][y]=Math.random()*2 - 1;
			   }
		   }
		return a;
	}
	public double[][] Transpose(double a[][])//Function to transpose an array 
	{
		double b[][]=new double[a[0].length][a.length];
		for(int x=0;x<a[0].length;x++)
		   {
			   for(int y=0;y<a.length;y++)
			   {
				  b[x][y]=a[y][x];
			   }
		   }
		return b;
	}
	public double[][] add(double a[][],double b[][])
	{
		double r[][]= {{0.0,0.0},{0.0,0.0}};//Default array to return if addition is not possible
		//Checking if addition is possible
	    if((a.length != b.length)||(a[0].length != b[0].length))
	    {
	    	System.out.println("Addition is not possible");
	    	return r;//Should find a way to terminate the flow here without having to return
	    }
	    double AD[][]=new double[a.length][a[0].length];
	    for(int x=0;x<a.length;x++)
		   {
			   for(int y=0;y<a[0].length;y++)
			   {
				  AD[x][y]=a[x][y]+b[x][y];
			   }
		   }
	    return AD;
	}
	public double[][] MatrixConvert(double a[])//Function to convert single dimensional array to double dimensional array
	{
		int l=a.length;
		double M[][]=new double[l][1];
		for(int i=0;i<l;i++)
		{
			M[i][0]=a[i];
		}
		return M;
	}
	public double[] ArrayConvert(double a[][])
	{
		int l=a.length;
		double A[]=new double[l];
		for(int i=0;i<l;i++)
		{
			A[i]=a[i][0];
		}
		return A;
	}
	
}
