/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 1997-2017 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */

package com.sun.faces.facelets.compiler;

import com.sun.faces.facelets.tag.TagLibrary;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Jacob Hookom
 * @version $Id$
 */
final class NamespaceManager {

    private final static class NS {
        public final String prefix;

        public final String namespace;

        public NS(String prefix, String ns) {
            this.prefix = prefix;
            this.namespace = ns;
        }
    }

    private final List namespaces;

    /**
     * 
     */
    public NamespaceManager() {
        this.namespaces = new ArrayList();
    }

    public void reset() {
        this.namespaces.clear();
    }

    public void pushNamespace(String prefix, String namespace) {
        NS ns = new NS(prefix, namespace);
        this.namespaces.add(0, ns);
    }

    public String getNamespace(String prefix) {
        NS ns = null;
        for (int i = 0; i < this.namespaces.size(); i++) {
            ns = (NS) this.namespaces.get(i);
            if (ns.prefix.equals(prefix)) {
                return ns.namespace;
            }
        }
        return null;
    }

    public void popNamespace(String prefix) {
        NS ns = null;
        for (int i = 0; i < this.namespaces.size(); i++) {
            ns = (NS) this.namespaces.get(i);
            if (ns.prefix.equals(prefix)) {
                this.namespaces.remove(i);
                return;
            }
        }
    }
    
    public NamespaceUnit toNamespaceUnit(TagLibrary library) {
        NamespaceUnit unit = new NamespaceUnit(library);
        if (this.namespaces.size() > 0) {
            NS ns = null;
            for (int i = this.namespaces.size() - 1; i >= 0; i--) {
                ns = (NS) this.namespaces.get(i);
                unit.setNamespace(ns.prefix, ns.namespace);
            }
        }
        return unit;
    }

}
