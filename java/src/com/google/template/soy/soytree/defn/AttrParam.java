/*
 * Copyright 2018 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.template.soy.soytree.defn;

import com.google.common.base.CaseFormat;
import com.google.template.soy.base.SourceLocation;
import com.google.template.soy.basetree.CopyState;
import com.google.template.soy.types.ast.TypeNode;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

/**
 * An explicitly declared template state attribute.
 *
 * <p>Important: Do not use outside of Soy code (treat as superpackage-private).
 */
public final class AttrParam extends TemplateParam implements TemplateHeaderVarDefn {
  private static final Pattern ATTR_NAME = Pattern.compile("^[a-z_][a-z_\\d]*(-[a-z_\\d]+)*$");

  public static String attrToParamName(String attrName) {
    return CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL, attrName);
  }

  public static boolean isValidAttrName(String attrName) {
    return ATTR_NAME.matcher(attrName).matches();
  }

  private final String originalAttributeName;

  public AttrParam(
      String name,
      boolean optional,
      @Nullable TypeNode typeNode,
      @Nullable String desc,
      @Nullable SourceLocation nameLocation,
      SourceLocation sourceLocation) {
    super(
        attrToParamName(name), nameLocation, sourceLocation, typeNode, false, optional, desc, null);
    this.originalAttributeName = name;
  }

  private AttrParam(AttrParam old) {
    super(old);
    this.originalAttributeName = old.originalAttributeName;
  }

  @Override
  public AttrParam copy(CopyState copyState) {
    return new AttrParam(this);
  }
}
