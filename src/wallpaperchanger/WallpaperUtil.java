/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wallpaperchanger;

import static com.sun.jna.Library.OPTION_FUNCTION_MAPPER;
import static com.sun.jna.Library.OPTION_TYPE_MAPPER;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.win32.StdCallLibrary;
import com.sun.jna.win32.W32APIFunctionMapper;
import com.sun.jna.win32.W32APITypeMapper;
import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;

/**
 *
 * @author vigya
 */
public class WallpaperUtil {
    
    public static final String[] EXTENSIONS = new String[]{
        "jpg", "JPG" // and other formats you need
    };
  public  static final FilenameFilter IMAGE_FILTER = new FilenameFilter() {
        @Override
        public boolean accept(final File dir, final String name) {
            for (final String ext : EXTENSIONS) {
                if (name.endsWith("." + ext)) {
                    return (true);
                }
            }
            return (false);
        }
    };
    
    
       public interface SPI extends StdCallLibrary {
        long SPI_SETDESKWALLPAPER = 20;
        long SPIF_UPDATEINIFILE = 0x01;
        long SPIF_SENDWININICHANGE = 0x02;

        SPI INSTANCE = (SPI) Native.loadLibrary("user32", SPI.class, new HashMap<Object, Object>() {
            {
                put(OPTION_TYPE_MAPPER, W32APITypeMapper.UNICODE);
                put(OPTION_FUNCTION_MAPPER, W32APIFunctionMapper.UNICODE);
            }
        });

        boolean SystemParametersInfo(
            WinDef.UINT_PTR uiAction,
            WinDef.UINT_PTR uiParam,
            String pvParam,
            WinDef.UINT_PTR fWinIni
        );
  
    }

    public static void setWallpaper(String filePath){
        System.out.println(filePath);
        SPI.INSTANCE.SystemParametersInfo(
        
          new WinDef.UINT_PTR(SPI.SPI_SETDESKWALLPAPER), 
          new WinDef.UINT_PTR(0), 
          filePath, 
          new WinDef.UINT_PTR(SPI.SPIF_UPDATEINIFILE | SPI.SPIF_SENDWININICHANGE));
    }
    
}
