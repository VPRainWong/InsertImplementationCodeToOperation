package com.vp.plugin.sample.insertjavaoperationcode.actions;

import com.vp.plugin.ApplicationManager;
import com.vp.plugin.DiagramManager;
import com.vp.plugin.action.VPAction;
import com.vp.plugin.action.VPActionController;
import com.vp.plugin.diagram.IClassDiagramUIModel;
import com.vp.plugin.diagram.shape.IClassUIModel;
import com.vp.plugin.model.IClass;
import com.vp.plugin.model.IImplModel;
import com.vp.plugin.model.IJavaOperationCodeDetail;
import com.vp.plugin.model.IOperation;
import com.vp.plugin.model.factory.IModelElementFactory;

public class InsertJavaOperationCodeActionControl implements VPActionController {

	@Override
	public void performAction(VPAction arg0) {
		// create blank class diagram
		DiagramManager diagramManager = ApplicationManager.instance().getDiagramManager();
		IClassDiagramUIModel diagram = (IClassDiagramUIModel) diagramManager.createDiagram(DiagramManager.DIAGRAM_TYPE_CLASS_DIAGRAM);
		
		// create class model and shape
		IClass myClass = IModelElementFactory.instance().createClass();
		myClass.setName("MyClass");
		IClassUIModel myClassShape = (IClassUIModel) diagramManager.createDiagramElement(diagram, myClass);
		myClassShape.setBounds(100, 100, 110, 40);
		myClassShape.setRequestResetCaption(true);
		
		// create operation to class model
		IOperation operation = myClass.createOperation();
		operation.setName("myOperation");
		operation.setVisibility(IOperation.VISIBILITY_PUBLIC);
		
		// retrieve Java code detail from operation 
		IJavaOperationCodeDetail javaDetail = operation.getJavaDetail();
		if (javaDetail == null) {
			javaDetail = IModelElementFactory.instance().createJavaOperationCodeDetail();
		}

		// retrieve implementation model from Java code detail
		IImplModel implModel = javaDetail.getImplModel();
		if (implModel == null) {
			implModel = IModelElementFactory.instance().createImplModel();			
		}
		// specify the implementation code
		implModel.setCode("//To Do");
		
		// set the implementation model to java detail
		javaDetail.setImplModel(implModel);
		
		// set the Java code detail to operation
		operation.setJavaDetail(javaDetail);

		// show up the diagram		
		diagramManager.openDiagram(diagram);

	}

	@Override
	public void update(VPAction arg0) {
		// TODO Auto-generated method stub
		
	}

}
