package hadoop.tools;

import java.util.function.Consumer;

/**
 *
 */
public class Runer {

    public static void run(String[] args, Consumer<String []> localfun,Consumer<String[]> ramotefun){
        if(args.length>=1 && args[0].equals("--local")){
            localfun.accept(args);
        }else{
            ramotefun.accept(args);
        }
    }
}
