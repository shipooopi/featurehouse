package composer.rules.meta;


import composer.rules.AsmetaLFunctionOverriding;
import de.ovgu.cide.fstgen.ast.FSTNonTerminal;
import de.ovgu.cide.fstgen.ast.FSTTerminal;

public class AsmetaLFunctionOverridingMeta extends AsmetaLFunctionOverriding{

	
	@Override
	public void compose(FSTTerminal terminalA, FSTTerminal terminalB,
			FSTTerminal terminalComp, FSTNonTerminal nonterminalParent){
		
		//terminalA.getFeatureName();
		int indexEqualA = terminalA.getBody().indexOf("=");
		int indexEqualB = terminalB.getBody().indexOf("=");
		String functionBodyB = terminalB.getBody().substring(indexEqualB + 1);
		
		StringBuilder newBody = new StringBuilder(terminalA.getBody());
		
		//TODO: replace "true" with FeatureVar name
		newBody.insert(indexEqualA + 1, "\n\t if true then");
		newBody.append("\n\t else \n\t\t " + functionBodyB +"\n\t endif");
		
		terminalA.setBody(newBody.toString());
		
		super.compose(terminalA, terminalB, terminalComp, nonterminalParent);
	}
	
}