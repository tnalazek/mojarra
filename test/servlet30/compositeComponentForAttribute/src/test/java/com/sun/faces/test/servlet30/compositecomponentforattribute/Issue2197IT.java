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

package com.sun.faces.test.servlet30.compositecomponentforattribute;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlTextInput;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSubmitInput;
import javax.faces.component.NamingContainer;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class Issue2197IT {

    private String webUrl;
    private WebClient webClient;

    @Before
    public void setUp() {
        webUrl = System.getProperty("integration.url");
        webClient = new WebClient();
    }

    @After
    public void tearDown() {
        webClient.close();
    }

    @Test
    public void testConvertNumber() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/convertNumber.xhtml");
        HtmlTextInput input = (HtmlTextInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "register"
                + NamingContainer.SEPARATOR_CHAR
                + "name");
        input.setValueAttribute("Foo");
        HtmlSubmitInput submit = (HtmlSubmitInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "button");
        page = (HtmlPage) submit.click();
        assertTrue(page.asText().contains("could not be understood as a percentage"));
    }

    @Test
    public void testConverter() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/converter.xhtml");
        HtmlTextInput input = (HtmlTextInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "register"
                + NamingContainer.SEPARATOR_CHAR
                + "name");
        input.setValueAttribute("Foo");
        HtmlSubmitInput submit = (HtmlSubmitInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "button");
        page = (HtmlPage) submit.click();
        assertTrue(page.asText().contains("must be a number consisting of one or more digits"));
    }

    @Test
    public void testConvertDateTime() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/convertDateTime.xhtml");
        HtmlTextInput input = (HtmlTextInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "register"
                + NamingContainer.SEPARATOR_CHAR
                + "name");
        input.setValueAttribute("Foo");
        HtmlSubmitInput submit = (HtmlSubmitInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "button");
        page = (HtmlPage) submit.click();
        assertTrue(page.asText().contains("could not be understood as a date"));
    }

    @Test
    public void testValidateLength() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/validateLength.xhtml");
        HtmlTextInput input = (HtmlTextInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "register"
                + NamingContainer.SEPARATOR_CHAR
                + "name");
        input.setValueAttribute("FooFoo");
        HtmlSubmitInput submit = (HtmlSubmitInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "button");
        page = (HtmlPage) submit.click();
        assertTrue(page.asText().contains("Length is greater than allowable maximum"));
    }

    @Test
    public void testActionListener() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/actionListener.xhtml");
        HtmlSubmitInput submit = (HtmlSubmitInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "mybutton"
                + NamingContainer.SEPARATOR_CHAR
                + "name");
        page = (HtmlPage) submit.click();
        assertTrue(page.asText().contains("name was pressed"));
    }

    @Test
    public void testValueChangeListener() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/valueChangeListener.xhtml");
        HtmlTextInput input = (HtmlTextInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "register"
                + NamingContainer.SEPARATOR_CHAR
                + "name");
        input.setValueAttribute("FooFoo");
        HtmlSubmitInput submit = (HtmlSubmitInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "button");
        page = (HtmlPage) submit.click();
        assertTrue(page.asText().contains("name value was changed"));
    }

    @Test
    public void testSetPropertyActionListener() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/setPropertyActionListener.xhtml");
        HtmlSubmitInput submit = (HtmlSubmitInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "mybutton"
                + NamingContainer.SEPARATOR_CHAR
                + "name");
        page = (HtmlPage) submit.click();
        assertTrue(page.asText().contains("foo"));
    }

    @Test
    public void testValidateDoubleRange() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/validateDoubleRange.xhtml");
        HtmlTextInput input = (HtmlTextInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "register"
                + NamingContainer.SEPARATOR_CHAR
                + "name");
        input.setValueAttribute("123456");
        HtmlSubmitInput submit = (HtmlSubmitInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "button");
        page = (HtmlPage) submit.click();
        assertTrue(page.asText().contains("Specified attribute is not between the expected values of 2 and 5"));
    }

    @Test
    public void testValidateLongRange() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/validateLongRange.xhtml");
        HtmlTextInput input = (HtmlTextInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "register"
                + NamingContainer.SEPARATOR_CHAR
                + "name");
        input.setValueAttribute("123456");
        HtmlSubmitInput submit = (HtmlSubmitInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "button");
        page = (HtmlPage) submit.click();
        assertTrue(page.asText().contains("Specified attribute is not between the expected values of 2 and 5"));
    }

    @Test
    public void testValidateRequired() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/validateRequired.xhtml");
        HtmlSubmitInput submit = (HtmlSubmitInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "button");
        page = (HtmlPage) submit.click();
        assertTrue(page.asText().contains("Value is required"));
    }

    @Test
    public void testValidator() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/validator.xhtml");
        HtmlTextInput input = (HtmlTextInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "register"
                + NamingContainer.SEPARATOR_CHAR
                + "name");
        input.setValueAttribute("123456");
        HtmlSubmitInput submit = (HtmlSubmitInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "button");
        page = (HtmlPage) submit.click();
        assertTrue(page.asText().contains("name was validated"));
    }

    @Test
    public void testValidateRegex() throws Exception {
        HtmlPage page = webClient.getPage(webUrl + "faces/validateRegex.xhtml");
        HtmlTextInput input = (HtmlTextInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "register"
                + NamingContainer.SEPARATOR_CHAR
                + "name");
        input.setValueAttribute("$$$$$$$$$$$");
        HtmlSubmitInput submit = (HtmlSubmitInput) page.getElementById("form"
                + NamingContainer.SEPARATOR_CHAR
                + "button");
        page = (HtmlPage) submit.click();
        assertTrue(page.asText().contains("Regex Pattern not matched"));
    }
}
