package javax.annotation;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.io.IOException;
import java.io.InputStream;

import javax.annotation.security.RolesAllowed;
import javax.annotation.sql.DataSourceDefinition;

import org.junit.jupiter.api.Test;

/**
 * <p>
 * {@code module-info.java} is compiled with JDK9 (or the build fails).
 * </p>
 *
 * <p>
 * This integration test ensures the source is compiled with JDK8.  JDK8 is 
 * the minimum version as some annotations make use of {@link java.lang.annotation.Repeatable}
 * </p>
 */
public class EnforceJavaVersionIntegrationTest {

	private static final int CLASS_FILE_MAJOR_VERSION_8 = 52;
	private static final byte[] CAFEBABE = { (byte) 0xCA, (byte) 0xFE, (byte) 0xBA, (byte) 0xBE };


	@Test
	public void ensureClassesAreTargetedForJavaVersionEight() throws IOException
	{
		assertJavaMajorVersion(PostConstruct.class, CLASS_FILE_MAJOR_VERSION_8);
		assertJavaMajorVersion(RolesAllowed.class, CLASS_FILE_MAJOR_VERSION_8);
		assertJavaMajorVersion(DataSourceDefinition.class, CLASS_FILE_MAJOR_VERSION_8);
	}


	private void assertJavaMajorVersion(Class<?> type, int classFileMajorVersion) throws IOException
	{
		String name = type.getCanonicalName().replace('.', '/') + ".class";
		ClassLoader loader = type.getClassLoader();

		try(InputStream inputStream = loader.getResourceAsStream(name)) {
			byte[] bytes = readFirstEightBytes(inputStream);

			assertMagicNumber(bytes);
			assertTargetVersion(bytes, classFileMajorVersion);
		}
	}


	private static void assertMagicNumber(byte[] compiled)
	{
		assertThat(compiled[0], is(CAFEBABE[0]));
		assertThat(compiled[1], is(CAFEBABE[1]));
		assertThat(compiled[2], is(CAFEBABE[2]));
		assertThat(compiled[3], is(CAFEBABE[3]));
	}


	private void assertTargetVersion(byte[] compiled, int classFileMajorVersion)
	{
		int inClassFile = compiled[7];
		assertThat(inClassFile, is(classFileMajorVersion));
	}


	private static byte[] readFirstEightBytes(InputStream in) throws IOException
	{
		byte[] buf = new byte[8];
		int b, i = 0;
		while((b = in.read()) != -1 && i < 8) {
			buf[i++] = (byte) b;
		}
		return buf;
	}
}
