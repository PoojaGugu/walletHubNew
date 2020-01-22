package UITestFrameWork;
import java.util.Date;
import org.apache.commons.codec.binary.Base64;
public class Utilities {

    /**
     *
     *
     * @return returns the date and time in string
     */
    public static  String getTime()
    {
        Date today = new Date();
        return String.valueOf(today.getTime());
    }
    /**
     *funtiion encodes string
     * @param  encoded
     * @return  decoded String
     */
    public static String decodeBase64(String encoded) {
        byte[] byteArray = Base64.decodeBase64(encoded);
        String decodedString = new String(byteArray);
        return decodedString;
    }

}





