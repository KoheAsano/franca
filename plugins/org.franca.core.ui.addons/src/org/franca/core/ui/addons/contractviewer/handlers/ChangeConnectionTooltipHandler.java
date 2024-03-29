/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.de).
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package org.franca.core.ui.addons.contractviewer.handlers;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.franca.core.ui.addons.contractviewer.FrancaContractVisualizerView;

public class ChangeConnectionTooltipHandler extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		FrancaContractVisualizerView view = FrancaContractVisualizerView.getInstance();
        if (view != null) {
        	view.invertLabelPresentation();
        }
		return null;
	}
}
