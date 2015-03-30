/*******************************************************************************
* This file has been generated by Franca's FDeployGenerator.
* Source: deployment spec 'org.example.spec.SpecCompoundHosts'
*******************************************************************************/
package org.example.spec;


/**
 * Interface for data deployment properties for 'org.example.spec.SpecCompoundHosts' specification
 * 
 * This is the data types related part only.
 */		
abstract class AbstractSpecCompoundHostsDataPropertyAccessor implements ISpecCompoundHostsDataPropertyAccessor
{

	protected StringProp convertStringProp (String val) {
		if (val.equals("u"))
			return StringProp.u; else 
		if (val.equals("v"))
			return StringProp.v; else 
		if (val.equals("w"))
			return StringProp.w; else 
		if (val.equals("x"))
			return StringProp.x; else 
		if (val.equals("y"))
			return StringProp.y; else 
		if (val.equals("z"))
			return StringProp.z;
		return null;
	}

}