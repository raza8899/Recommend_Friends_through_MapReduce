import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.IntWritable;

public class ImpArrayWritable  extends ArrayWritable{
	
 
 public ImpArrayWritable()
{

	super(IntWritable.class);
}
}
