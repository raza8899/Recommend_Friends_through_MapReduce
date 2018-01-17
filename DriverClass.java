
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class DriverClass {

	public static void main(String[] args) throws Exception {

		if (args.length != 2)
			System.exit(1);

		Configuration conf = new Configuration();
		Job jm = new Job(conf, "Mutual Friends");

		jm.setJarByClass(DriverClass.class); // Set the Driver Class
		jm.setMapperClass(MutualMapper.class);
		jm.setReducerClass(MutualReducer.class);
		jm.setMapOutputKeyClass(IntWritable.class);
		jm.setMapOutputValueClass(ImpArrayWritable.class);

		jm.setOutputKeyClass(IntWritable.class);
		jm.setOutputValueClass(org.apache.hadoop.io.Text.class);

		FileInputFormat.addInputPath(jm, new Path(args[0]));
		FileOutputFormat.setOutputPath(jm, new Path(args[1]));

		System.exit(jm.waitForCompletion(true) ? 0 : 1);

	}

}
