package Neural_Network;
public class Multilayer_Perceptron 
{
	Matrix obj=new Matrix();
	int input;
	int hidden;
	int No_of_hidden;
	int output;
	Multilayer_Perceptron(int a, int b, int c,int d)
	{
		input=a;
		hidden=b;
		No_of_hidden=c;
		output=d;
	}
	double Hidden_Values[][][];
	double Weight_inp_hid[][];
	double Weight_hid[][][];
	double Weight_hid_out[][];
	double Bias_hidden[][];
	double Bias_output[];
	double lr;
	void initialize()
	{
		Hidden_Values=new double[No_of_hidden][hidden][1];
    	Weight_inp_hid=obj.Randomize(new double[hidden][input]);
    	Weight_hid=new double[No_of_hidden][hidden][hidden];//3D array to store all of the weights in the hidden layers
    	Weight_hid_out=obj.Randomize(new double[output][hidden]);
    	for(int i=0;i<No_of_hidden;i++)
		{
			Weight_hid[i]=obj.Randomize(Weight_hid[i]);
		}
    	Bias_hidden=obj.Randomize(new double[No_of_hidden][hidden]);
    	Bias_output=new double[output];
    	
	}
	public double[] feedforward(double a[])
    {
    	double I[][]=new double[a.length][1];
    	I=obj.MatrixConvert(a);
    	Hidden_Values[0]=obj.MapFunction(obj.add(obj.Multiply(Weight_inp_hid, I),obj.MatrixConvert(Bias_hidden[0])),"sigmoid");
    	for(int i=1;i<No_of_hidden;i++)
    	{
    		Hidden_Values[i]=obj.MapFunction(obj.add((obj.Multiply(Weight_hid[i], Hidden_Values[i-1])),obj.MatrixConvert(Bias_hidden[i])),"sigmoid");
    	}
    	double O[]=new double[output];
    	O=obj.ArrayConvert(obj.MapFunction(obj.add(obj.Multiply(Weight_hid_out, Hidden_Values[No_of_hidden -1]),obj.MatrixConvert(Bias_output)),"sigmoid"));
    	return O;
    }
	public void train(double inputs[][], double targets[][],double a)
	{
		for(int j=0;j<inputs.length;j++)
		{
			lr=a;
			double Ntarget[][]=obj.ScalarMultiply(obj.MatrixConvert(feedforward(inputs[j])), -1); 
			double e[][]=(obj.add(obj.MatrixConvert(targets[j]), Ntarget));
			double Hidden_errors[][][]=new double[No_of_hidden][hidden][1];
			Hidden_errors[0]=obj.Multiply((obj.Transpose(Weight_hid_out)), e);
			for(int i=1;i<No_of_hidden;i++)// Not sure if this is correct.
			{
				Hidden_errors[i]=obj.Multiply((obj.Transpose(Weight_hid[No_of_hidden-i])), Hidden_errors[i-1]);
			}
			double Gradient_output[][];
			Gradient_output=obj.SAMultiply((obj.MapFunction((obj.MatrixConvert(feedforward(inputs[j]))), "Dsigmoid")),e);
			double Delta_O_Weights[][]=obj.ScalarMultiply((obj.Multiply(Gradient_output, (obj.Transpose(Hidden_Values[No_of_hidden-1])))),lr);
			Weight_hid_out=obj.add(Weight_hid_out, (obj.ScalarMultiply(Delta_O_Weights, -1)));
			Bias_output=obj.ArrayConvert(obj.add(obj.MatrixConvert(Bias_output), (obj.ScalarMultiply(Gradient_output, -1))));
			double Gradient_hidden[][][]=new double[No_of_hidden][][];
			double Delta_H_Weights[][][]=new double[No_of_hidden][][];
			for(int i=1;i<=No_of_hidden;i++)
			{
				Gradient_hidden[No_of_hidden-i]=obj.SAMultiply((obj.MapFunction(Hidden_Values[No_of_hidden-i], "Dsigmoid")),Hidden_errors[i-1]);
				if(No_of_hidden-i-1==-1)
				{
					Delta_H_Weights[No_of_hidden-i]=obj.ScalarMultiply((obj.Multiply(Gradient_hidden[No_of_hidden-i], (obj.Transpose(obj.MatrixConvert(inputs[j]))))),lr);
				}
				else
				{
					Delta_H_Weights[No_of_hidden-i]=obj.ScalarMultiply((obj.Multiply(Gradient_hidden[No_of_hidden-i], (obj.Transpose(Hidden_Values[No_of_hidden-i])))),lr);
				}
			}
			Weight_inp_hid=obj.add(Weight_inp_hid, (obj.ScalarMultiply(Delta_H_Weights[0], -1)));
			for(int i=1;i<No_of_hidden;i++)
			{
				Weight_hid[i]=obj.add(Weight_hid[i], (obj.ScalarMultiply(Delta_H_Weights[i], -1)));
				Bias_hidden[i]=obj.ArrayConvert(obj.add(obj.MatrixConvert(Bias_hidden[i]), (obj.ScalarMultiply(Gradient_hidden[i], -1))));
			}  
		}
		
	}
}
