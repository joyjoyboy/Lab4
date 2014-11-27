import junit.framework.*;
import com.mockobjects.servlet.*;

public class TestTestingLabConverterServlet extends TestCase {

	public void test_bad_parameter() throws Exception {
		TestingLabConverterServlet servlet = new TestingLabConverterServlet();
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		request.setupAddParameter("farenheitTemperature", "badtest");
		response.setExpectedContentType("text/html");
		servlet.doGet(request,response);
		response.verify();
		assertEquals(
"<html><head><title>Bad Temperature</title></head><body><h2>Need to enter a valid temperature!Got a NumberFormatException on badtest</h2></body></html>\n",
								 response.getOutputStreamContents());
	}

	public void test_null_parameter() throws Exception {
		TestingLabConverterServlet servlet = new TestingLabConverterServlet();
		MockHttpServletRequest request = new MockHttpServletRequest();
		MockHttpServletResponse response = new MockHttpServletResponse();
		
		response.setExpectedContentType("text/html");
		servlet.doGet(request,response);
		response.verify();
		assertEquals("<html><head><title>No Temperature</title>" + "</head><body><h2>Need to enter a temperature!" + "</h2></body></html>\n", response.getOutputStreamContents());
	}

}

