package ua.step.example.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ua.step.example.utils.Injection;
 
public class Task03Test {
 
    class Injectable {        
    }
     
    class FieldWithSameClass {                
        private Injectable object = null;          
    }
         
    @Test
    public void testInjectObjectInTheFieldWithSameClass() {                
        assertNotNull(Injection.injectAll(new FieldWithSameClass(), 
                new Injectable()).object);
    }
     
    class FieldsWithSameClass {                
        private Injectable object1 = null;          
        private Injectable object2 = null;
    }
     
    @Test
    public void testMultipleInjectionInTheFieldsWithSameClass() {                
        final Injectable injectable = new Injectable();
        FieldsWithSameClass source = 
        		Injection.injectAll(new FieldsWithSameClass(), injectable);
         
        assertNotNull(source.object1);
        assertNotNull(source.object2);
        assertSame(injectable, source.object1);
        assertSame(source.object1, source.object2);    
    }
     
    class InjectableWithInterface implements InjectableI {        
    }
     
    interface InjectableI {        
    }
     
    class FieldWithClassImplementsInterface {                
        private InjectableWithInterface object = null;          
    }
     
    @Test
    public void testInjectObjectInTheFieldWithClassImplementsInterface() {                
        assertNotNull(Injection.injectAll(new FieldWithClassImplementsInterface(), 
                new InjectableWithInterface()).object);
    }
     
    class FieldWithInterface {                
        private InjectableI object = null;          
    }
     
    @Test
    public void testInjectObjectInTheFieldWithInterface() {                
        final InjectableI injectable = new InjectableWithInterface();
        assertNotNull(Injection.injectAll(new FieldWithInterface(), 
                injectable).object);
    }
     
    class InjectableParent {        
    }
     
    class InjectableChild extends InjectableParent {        
    }
     
    class FieldWithExtends {                
        private InjectableParent object = null;          
    }
     
    @Test
    public void testInjectObjectInTheFieldWithExtends_injectChild() {                
        assertNotNull(Injection.injectAll(new FieldWithExtends(), 
                new InjectableChild()).object);
    }
     
    @Test
    public void testInjectObjectInTheFieldWithExtends_injectParent() {                
        assertNotNull(Injection.injectAll(new FieldWithExtends(), 
                new InjectableParent()).object);
    }
     
    class FieldWithObject {
        private Object object1;
        private Injectable object2;
    }
     
    @Test
    public void testNoObjectInjection() {                
        final FieldWithObject source = 
        		Injection.injectAll(new FieldWithObject(), new Injectable());
        assertNull(source.object1);
        assertNotNull(source.object2);
    }
     
    class FieldWithObjectOnly {
        private Object object;

		public Object getObject() {
			return object;
		}

		public void setObject(Object object) {
			this.object = object;
		}    
    }
     
    @Test
    public void testNoInjectionExceptionIfObjectPresent() {                
        try {
        	Injection.injectAll(new FieldWithObjectOnly(), new Injectable());
            fail("Бес попутал, должон быть exception");
        } catch (IllegalArgumentException e) {
            assertEquals("В исходном объекте не нашлось ни одного поля по образу и подобию.", 
                    e.getMessage());
        }
    }
     
    class FieldWithoutObjects {
    }
     
    @Test
    public void testNoInjectionExceptionIfNoObjects() {                
        try {
        	Injection.injectAll(new FieldWithoutObjects(), new Injectable());
            fail("Бес попутал, должон быть exception");
        } catch (IllegalArgumentException e) {
            assertEquals("В исходном объекте не нашлось ни одного поля по образу и подобию.", 
                    e.getMessage());
        }
    }    
}