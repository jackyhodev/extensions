package dev.galasa.eclipse.preferences;

import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import dev.galasa.eclipse.Activator;

public class GalasaPreferences extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

	StringFieldEditor bootstrapField;
	StringFieldEditor overridesField;
	StringFieldEditor requestorField;
	StringFieldEditor remoteMavenField;
	
	public GalasaPreferences() {
		super(GRID);

		IPreferenceStore store = Activator.getInstance().getPreferenceStore(); 

		setPreferenceStore(store);
		setDescription("Galasa Preferences");

		store.addPropertyChangeListener(this);
	}
	
	@Override
	public void init(IWorkbench arg0) {
	}

	@Override
	protected void createFieldEditors() {
		bootstrapField   = new StringFieldEditor(PreferenceConstants.P_BOOTSTRAP_URI,   "Bootstrap URI:",            getFieldEditorParent());
		overridesField   = new StringFieldEditor(PreferenceConstants.P_OVERRIDES_URI,   "Overrides URI:",            getFieldEditorParent());
		requestorField   = new StringFieldEditor(PreferenceConstants.P_REQUESTOR_ID,    "TEMPORARY - Requestor ID:", getFieldEditorParent());
		remoteMavenField = new StringFieldEditor(PreferenceConstants.P_REMOTEMAVEN_URI, "Remote Maven URI:",         getFieldEditorParent());
		
		bootstrapField.setErrorMessage("Bootstrap URI is required");
		bootstrapField.setEmptyStringAllowed(false);
		
		addField(bootstrapField);
		addField(overridesField);
		addField(requestorField);
		addField(remoteMavenField);
	}
	
}