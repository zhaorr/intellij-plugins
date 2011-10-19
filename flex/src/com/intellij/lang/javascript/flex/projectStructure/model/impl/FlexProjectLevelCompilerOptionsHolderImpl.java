package com.intellij.lang.javascript.flex.projectStructure.model.impl;

import com.intellij.lang.javascript.flex.projectStructure.FlexProjectLevelCompilerOptionsHolder;
import com.intellij.lang.javascript.flex.projectStructure.model.ModifiableCompilerOptions;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import com.intellij.openapi.components.StorageScheme;
import com.intellij.openapi.project.Project;
import com.intellij.util.xmlb.annotations.Property;

@State(
  name = "FlexIdeProjectLevelCompilerOptionsHolder",
  storages = {
    @Storage(file = "$WORKSPACE_FILE$"),
    @Storage(file = "$PROJECT_CONFIG_DIR$/flexCompiler.xml", scheme = StorageScheme.DIRECTORY_BASED)
  }
)
public class FlexProjectLevelCompilerOptionsHolderImpl extends FlexProjectLevelCompilerOptionsHolder
  implements PersistentStateComponent<FlexProjectLevelCompilerOptionsHolderImpl.State> {

  private final CompilerOptionsImpl myModel;

  public FlexProjectLevelCompilerOptionsHolderImpl(final Project project) {
    myModel = new CompilerOptionsImpl(project, true);
  }

  public FlexProjectLevelCompilerOptionsHolderImpl.State getState() {
    FlexProjectLevelCompilerOptionsHolderImpl.State state = new State();
    state.compilerOptions = myModel.getState();
    return state;
  }

  @Override
  public ModifiableCompilerOptions getProjectLevelCompilerOptions() {
    return myModel;
  }

  public void loadState(final FlexProjectLevelCompilerOptionsHolderImpl.State state) {
    myModel.loadState(state.compilerOptions);
  }

  public static class State {
    @Property(surroundWithTag = false)
    public CompilerOptionsImpl.State compilerOptions = new CompilerOptionsImpl.State();
  }
}
