import java.io.File;
import java.util.Scanner;
import javax.swing.JOptionPane;

// main class
public class UpdateAll {

	// main method
	public static void main( String[] args ) {
		String path = JOptionPane.showInputDialog("Give me a path:");

		// path exists? is directory?
		if ( pathIsValid( path ) ) {
			updatePathContent( path );
		} else {
			System.out.println( "Path isn't valid.");
		}
	}

	// validating a path
	private static boolean pathIsValid( String path ) {
		File temp = new File( path );
		return ( temp.exists() && temp.isDirectory() );
	}

	// add slash to string
	private static String slash( String text ) {
		if ( text.charAt( text.length() - 1 ) != '/' ) {
			return text.concat( "/" );
		}
		return text;
	}

	// update this file
	private static void update( File file ) {
		String name = file.getName(), newName;
		if ( name.substring( 0, 3 ).equals( "___" ) ) {
			newName = name.substring( 3, name.length() );
			file.renameTo( new File( slash( file.getParent() ) + newName ) );
			System.out.println( name + " renamed to " + newName );
		}
	}

	// update path's content
	private static void updatePathContent( String path ) {
		File content[], file = new File( path );
		if ( file.isDirectory() ) {
			content = file.listFiles();
			for ( File item : content ) {
				updatePathContent( item.getAbsolutePath() );
			}
		}
		update( file );
	}

}
