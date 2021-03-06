/**
 * 
 */
package org.jhove2.module.format.tiff.type;

import org.jhove2.annotation.ReportableProperty;
import org.jhove2.core.reportable.AbstractReportable;

import com.sleepycat.persist.model.Persistent;

/**
 * @author mstrong
 *
 */
/** 
 * stores the Tiff SBYTE type.  
 * 8-bit signed(twos-complement) integer
 * 
 * @author mstrong
 *
 */
@Persistent
public class SByte 
    extends AbstractReportable {
    private byte value;

    public SByte(byte value) {
        this.value = value;
    }
    
    /**  no-arg constructor for Byte object */
    public SByte() {
    }

    @ReportableProperty(order = 1, value="Tag SBYTE value")
    public byte getValue(){
        return value;
    }
}

