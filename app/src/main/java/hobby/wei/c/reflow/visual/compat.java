package hobby.wei.c.reflow.visual;

import scala.reflect.ClassTag;
import scala.reflect.ClassTag$;

public class compat {
    public static class classTag {
        public static ClassTag<String> string = ClassTag$.MODULE$.apply(String.class);
    }
}
