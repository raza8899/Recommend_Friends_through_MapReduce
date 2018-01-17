import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class MutualMapper extends Mapper<Object, Text, IntWritable, ImpArrayWritable> {

	IntWritable ONE = new IntWritable(1);
	IntWritable ZERO = new IntWritable(0);
	
	public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

		String[] line = value.toString().split("\t");
		if(line.length<2)
			return;
		IntWritable acc_holder=new IntWritable(Integer.parseInt(line[0]));
		String friends[]= line[1].split(",");
		int[] friendslist =new int[friends.length];
		int i=0;
		for(i=0;i<friends.length;i++)
		  {
			int friend =Integer.parseInt(friends[i]);
			friendslist[i]= friend;
			IntWritable[] value1= {new IntWritable(friend),ZERO};
			IntWritable[] value2= {acc_holder,ZERO};
			ImpArrayWritable tuple1=new ImpArrayWritable();
			ImpArrayWritable tuple2=new ImpArrayWritable();
			tuple1.set(value1);
			tuple2.set(value2);
			context.write(acc_holder, tuple1);
			context.write(new IntWritable(friend), tuple2);
		  }		
		for(int guest1:friendslist)
		{
			IntWritable foreigner1=new IntWritable(guest1);
			for(int guest2 :friendslist)
			{ 
				IntWritable foreigner2=new IntWritable(guest2);
				if(guest1!=guest2)
				{
					IntWritable[] gst1= {foreigner2,ONE};
					ImpArrayWritable tuple =new ImpArrayWritable();
					tuple.set(gst1);
					context.write(foreigner1, tuple);
				}
				
			}
			
		}
			
			

	}
}
