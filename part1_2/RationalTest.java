import junit.framework.TestCase;

public class RationalTest extends TestCase {

    protected Rational HALF;

    protected void setUp() {
      HALF = new Rational( 1, 2 );
    }

    // Create new test
    public RationalTest (String name) {
        super(name);
    }

    public void testEquality() {
        assertEquals(new Rational(1,3), new Rational(1,3));
        assertEquals(new Rational(1,3), new Rational(2,6));
        assertEquals(new Rational(3,3), new Rational(1,1));
    }

    // Test for nonequality
    public void testNonEquality() {
        assertFalse(new Rational(2,3).equals(
            new Rational(1,3)));
    }

    public void testAccessors() {
    	assertEquals(new Rational(2,3).numerator(), 2);
    	assertEquals(new Rational(2,3).denominator(), 3);
    }

    public void testRoot() {
        Rational s = new Rational( 1, 4 );
		Rational o = new Rational(1000000, 1);
        Rational sRoot = null;
		Rational oRoot = null;
        try {
            sRoot = s.root();
			oRoot = o.root();
        } catch (IllegalArgumentToSquareRootException e) {
            e.printStackTrace();
        }
        assertTrue( sRoot.isLessThan( HALF.plus( Rational.getTolerance() ) ) 
                        && HALF.minus( Rational.getTolerance() ).isLessThan( sRoot ) );
    }

	public void testNULL() {
		Rational a = new Rational(-1, -1);
		Rational b = null;

		assertFalse(a.equals(b));
	}

	public void testDifferentClass() {
		Rational a = new Rational(-1, -1);
		Integer b = new Integer(1);

		assertFalse(a.equals(b));
	}

	public void testSetTolerance() {
		Rational a = new Rational(1, 10000);
		Rational b = new Rational(1, 4);

		b.setTolerance(a);
	}

	public void testAbs() {
		Rational a = new Rational(2, 3);
		Rational b = new Rational(-2, -3);
		Rational c = new Rational(2, -3);

		c.abs();
		assertEquals(a.abs().numerator(), b.abs().numerator());

	}

	public void testLessThan() {
		Rational c = new Rational(-1, 4);
		Rational d = new Rational(-1, 8);

		assertFalse(c.isLessThan(d));
	
	}

	public void testPlus1() {
		Rational a = new Rational(-1, -1);
		Rational b = new Rational(-2, -2);

		assertEquals(a.plus(b).numerator(), 2);

	}

	public void testPlus2() {
		Rational a = new Rational(3, 1);
		Rational b = new Rational(0, 2);

		assertEquals(a.plus(b).numerator(), 3);
	}

	public void testDivision() {
		Rational a = new Rational(-1, 2);
		Rational b = new Rational(3, -4);
		Rational c = new Rational(2, 0);

		assertEquals(a.divides(b).numerator(), 2);
		a.divides(c);
	}

    public static void main(String args[]) {
        String[] testCaseName = 
            { RationalTest.class.getName() };
        // junit.swingui.TestRunner.main(testCaseName);
        junit.textui.TestRunner.main(testCaseName);
    }
}
