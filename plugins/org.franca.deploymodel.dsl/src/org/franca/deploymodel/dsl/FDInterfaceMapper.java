/*******************************************************************************
* Copyright (c) 2012 Harman International (http://www.harman.com).
* All rights reserved. This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
*******************************************************************************/
package org.franca.deploymodel.dsl;

import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.franca.deploymodel.dsl.fDeploy.FDArgument;
import org.franca.deploymodel.dsl.fDeploy.FDArray;
import org.franca.deploymodel.dsl.fDeploy.FDAttribute;
import org.franca.deploymodel.dsl.fDeploy.FDBroadcast;
import org.franca.deploymodel.dsl.fDeploy.FDElement;
import org.franca.deploymodel.dsl.fDeploy.FDEnumValue;
import org.franca.deploymodel.dsl.fDeploy.FDEnumeration;
import org.franca.deploymodel.dsl.fDeploy.FDInterface;
import org.franca.deploymodel.dsl.fDeploy.FDMethod;
import org.franca.deploymodel.dsl.fDeploy.FDStruct;
import org.franca.deploymodel.dsl.fDeploy.FDStructField;
import org.franca.deploymodel.dsl.fDeploy.FDTypeDef;

import com.google.common.collect.Maps;

/**
 * The FDInterfaceMapper provides a mapping from the elements of a Franca 
 * model (i.e., the elements in a *.fidl file) to the property definitions
 * of a Franca deployment model.
 * 
 * For example, if there is a property definition in some *.fdepl-file for
 * a FMethod, the FDInterfaceMapper will map this FMethod to the corresponding
 * FDMethod object.
 */
public class FDInterfaceMapper {

	Map<EObject, FDElement> mapping = Maps.newHashMap();
	
	/**
	 * The constructor of FDInterfaceMapper will initialize the mapper
	 * from a given deployment definition for this interface.
	 * 
	 * @param fdi  the deployment definition for the FInterface. 
	 */
	public FDInterfaceMapper (FDInterface fdi) {
		init(fdi);
	}


	/**
	 * The actual mapping function. For an element of a Franca interface
	 * model it will provide the corresponding deployment element (if any).  
	 * 
	 * @param obj  the element of the Franca model
	 * @return the actual mapping or null (if no mapping available) 
	 */
	public FDElement getFDElement (EObject obj) {
		return mapping.get(obj);
	}
	
	
	// *****************************************************************************
	// private helpers for initialization
	
	private void init (FDInterface fdi) {
		mapping.put(fdi.getTarget(), fdi);
		for(FDAttribute e : fdi.getAttributes()) {
			mapping.put(e.getTarget(), e);
		}
		for(FDMethod e : fdi.getMethods()) {
			mapping.put(e.getTarget(), e);
			init(e.getInArguments());
			init(e.getOutArguments());
		}
		for(FDBroadcast e : fdi.getBroadcasts()) {
			mapping.put(e.getTarget(), e);
			init(e.getOutArguments());
		}
		
		for(FDTypeDef t : fdi.getTypes()) {
			if (t instanceof FDArray) {
				mapping.put(((FDArray) t).getTarget(), t);
			} else if (t instanceof FDStruct) {
				mapping.put(((FDStruct) t).getTarget(), t);
				for(FDStructField f : ((FDStruct) t).getFields()) {
					mapping.put(f.getTarget(), f);
				}
			} else if (t instanceof FDEnumeration) {
				mapping.put(((FDEnumeration) t).getTarget(), t);
				for(FDEnumValue e : ((FDEnumeration) t).getEnumerators()) {
					mapping.put(e.getTarget(), e);
				}
			}
		}
	}
	
	private void init (List<FDArgument> args) {
		for(FDArgument arg : args) {
			mapping.put(arg.getTarget(), arg);
		}
	}
}