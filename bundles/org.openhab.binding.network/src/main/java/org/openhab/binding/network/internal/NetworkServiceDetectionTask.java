/**
 * Copyright (c) 2010-2021 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.network.internal;

/**
 * The {@link NetworkServiceDetectionTask} is responsible for the concurrent work.
 *
 * @author Marcel Kanter - Initial contribution
 */
public class NetworkServiceDetectionTask implements Runnable {

    public String hostname;
    public Integer port;
    public Integer timeout;

    protected NetworkServiceHandler handler;

    public NetworkServiceDetectionTask(NetworkServiceHandler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
    }
}
