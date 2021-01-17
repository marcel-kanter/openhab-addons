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

import org.eclipse.jdt.annotation.NonNullByDefault;

/**
 * The {@link NetworkServiceConfiguration} class contains fields mapping thing configuration parameters.
 *
 * @author Marcel Kanter - Initial contribution
 */
@NonNullByDefault
public class NetworkServiceConfiguration {
    public String hostname = "";
    public Integer port = 0;
    public Integer refreshInterval = 60000;
}
