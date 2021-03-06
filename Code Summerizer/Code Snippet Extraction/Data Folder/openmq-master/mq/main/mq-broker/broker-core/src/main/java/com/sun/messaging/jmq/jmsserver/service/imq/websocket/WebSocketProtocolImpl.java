/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2000-2017 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://oss.oracle.com/licenses/CDDL+GPL-1.1
 * or LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at LICENSE.txt.
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

/*
 */ 

package com.sun.messaging.jmq.jmsserver.service.imq.websocket;

import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.nio.channels.spi.AbstractSelectableChannel;
import org.glassfish.grizzly.PortRange;
import com.sun.messaging.jmq.util.log.Logger;
import com.sun.messaging.jmq.jmsservice.BrokerEvent;
import com.sun.messaging.jmq.jmsserver.Broker;
import com.sun.messaging.jmq.jmsserver.license.LicenseBase;
import com.sun.messaging.jmq.jmsserver.util.BrokerException;
import com.sun.messaging.jmq.jmsserver.net.Protocol;
import com.sun.messaging.jmq.jmsserver.net.ProtocolStreams;
import com.sun.messaging.jmq.jmsserver.net.ProtocolCallback;
import com.sun.messaging.jmq.jmsserver.net.tcp.TcpProtocol;
import com.sun.messaging.jmq.jmsserver.Globals;
import com.sun.messaging.jmq.jmsserver.resources.*;
import com.sun.messaging.jmq.jmsserver.service.imq.grizzly.GrizzlyProtocolImpl;

/**
 * @author amyk
 */
public class WebSocketProtocolImpl extends GrizzlyProtocolImpl
{

    private static final int DEFAULT_WS_PORT = 7670; 
    private static final int DEFAULT_WSS_PORT = 7671;

    public WebSocketProtocolImpl(WebSocketIPService s, String proto) {
        super(s, proto);
        if (proto.equals("ws")) {
            port = DEFAULT_WS_PORT;
        } else {
            port = DEFAULT_WSS_PORT;
        }
    }

    @Override
    protected boolean isSSLProtocol() {
        if (proto.equals("wss")) { 
            return true;
        }
        return false;
    }

    public PortRange getPortRange() {
        if (port <= 0) {
            return new PortRange(1, 65535);
        }
        return new PortRange(port);
    }

}
