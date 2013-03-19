/*
 ***** BEGIN LICENSE BLOCK *****
 * Version: CPL 1.0/GPL 2.0/LGPL 2.1
 *
 * The contents of this file are subject to the Common Public
 * License Version 1.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a copy of
 * the License at http://www.eclipse.org/legal/cpl-v10.html
 *
 * Software distributed under the License is distributed on an "AS
 * IS" basis, WITHOUT WARRANTY OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * rights and limitations under the License.
 *
 * Copyright (C) 2009 Thomas E. Enebo <tom.enebo@gmail.com>
 * 
 * Alternatively, the contents of this file may be used under the terms of
 * either of the GNU General Public License Version 2 or later (the "GPL"),
 * or the GNU Lesser General Public License Version 2.1 or later (the "LGPL"),
 * in which case the provisions of the GPL or the LGPL are applicable instead
 * of those above. If you wish to allow use of your version of this file only
 * under the terms of either the GPL or the LGPL, and not to allow others to
 * use your version of this file under the terms of the CPL, indicate your
 * decision by deleting the provisions above and replace them with the notice
 * and other provisions required by the GPL or the LGPL. If you do not delete
 * the provisions above, a recipient may use your version of this file under
 * the terms of any one of the CPL, the GPL or the LGPL.
 ***** END LICENSE BLOCK *****/
package org.jrubyparser.ast;

import java.util.List;

import org.jrubyparser.NodeVisitor;
import org.jrubyparser.SourcePosition;

/**
 * Simple Node that allows editor projects to keep position info in AST
 * (evaluation does not need this).
 */
public class ArgumentNode extends Node implements INameNode, ILocalVariable {
    private String identifier;
    private int location;

    public ArgumentNode(SourcePosition position, String identifier) {
        super(position);

        this.identifier = identifier;
    }
    
    public ArgumentNode(SourcePosition position, String identifier, int location) {
        super(position);

        this.identifier = identifier;
        this.location = location;
    }

    public NodeType getNodeType() {
        return NodeType.ARGUMENTNODE;
    }
    
    public Object accept(NodeVisitor visitor) {
        throw new RuntimeException("ArgumentNode should never be evaluated");
    }

    /**
     * How many scopes should we burrow down to until we need to set the block variable value.
     *
     * @return 0 for current scope, 1 for one down, ...
     */
    public int getDepth() {
        return location >> 16;
    }

    /**
     * Gets the index within the scope construct that actually holds the eval'd value
     * of this local variable
     *
     * @return Returns an int offset into storage structure
     */
    public int getIndex() {
        return location & 0xffff;
    }
    
    public String getName() {
        return identifier;
    }
    
    public void setName(String name) {
        this.identifier = name;
    }

    public List<Node> childNodes() {
        return EMPTY_LIST;
    }

    // Fixme: Can we assert name in constructor and remove null check?
    public boolean isNameMatch(String name) {
        String thisName = getName();
        
        return thisName != null && thisName.equals(name);
    }
}
