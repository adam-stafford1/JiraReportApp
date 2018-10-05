package ut.com.adam.stafford;

import org.junit.Test;
import com.adam.stafford.api.MyPluginComponent;
import com.adam.stafford.impl.MyPluginComponentImpl;

import static org.junit.Assert.assertEquals;

public class MyComponentUnitTest
{
    @Test
    public void testMyName()
    {
        MyPluginComponent component = new MyPluginComponentImpl(null);
        assertEquals("names do not match!", "myComponent",component.getName());
    }
}