/**
 * JHOVE2 - Next-generation architecture for format-aware characterization
 *
 * Copyright (c) 2009 by The Regents of the University of California,
 * Ithaka Harbors, Inc., and The Board of Trustees of the Leland Stanford
 * Junior University.
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * o Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *
 * o Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * o Neither the name of the University of California/California Digital
 *   Library, Ithaka Harbors/Portico, or Stanford University, nor the names of
 *   its contributors may be used to endorse or promote products derived from
 *   this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.jhove2.persist.inmemory;

import org.jhove2.core.JHOVE2Exception;
import org.jhove2.module.identify.SourceIdentifier;
import org.jhove2.module.identify.Identifier;
import org.jhove2.persist.IdentifierAccessor;


/**
 * @author smorrissey
 *
 */


public class InMemoryIdentifierAccessor extends InMemoryBaseModuleAccessor
		implements IdentifierAccessor {
	
	/** File-level identifier module. */
	protected SourceIdentifier parentSourceIdentifier;
	
	public InMemoryIdentifierAccessor(){
		super();
	}

	/* (non-Javadoc)
	 * @see org.jhove2.persist.IdentifierAccessor#getFileSourceIdentifier(org.jhove2.module.identify.Identifier)
	 */
	@Override
	public SourceIdentifier getFileSourceIdentifier(Identifier module)
			throws JHOVE2Exception {
		return this.parentSourceIdentifier;
	}

	/* (non-Javadoc)
	 * @see org.jhove2.persist.IdentifierAccessor#setFileSourceIdentifier(org.jhove2.module.identify.Identifier, org.jhove2.module.identify.Identifier)
	 */
	@Override
	public SourceIdentifier setFileSourceIdentifier(Identifier module,
			SourceIdentifier fileSourceIdentifier) throws JHOVE2Exception {	
		if (module!=null && module.getModuleAccessor()!=null){
			module.getModuleAccessor().persistModule(module);
		}
		if (module==this.module){
			if (fileSourceIdentifier == null){
				if (this.parentSourceIdentifier != null){
					// remove this Identifier as parent of old SourceIdentifier
					this.parentSourceIdentifier.setParentIdentifierId(null);
				}
				this.parentSourceIdentifier=fileSourceIdentifier;
			}
			else {
				if (fileSourceIdentifier.getModuleAccessor()!=null){
					fileSourceIdentifier.getModuleAccessor().persistModule(fileSourceIdentifier);
				}
				if (this.parentSourceIdentifier != null && 
						this.parentSourceIdentifier != fileSourceIdentifier){
					// remove this Identifier as parent of old SourceIdentifier
					this.parentSourceIdentifier.setParentIdentifierId(null);
				}
				this.parentSourceIdentifier=fileSourceIdentifier;
				this.parentSourceIdentifier.setParentIdentifierId(module.getModuleId());
			}			
		}	
		
		return this.parentSourceIdentifier;
	}

}
