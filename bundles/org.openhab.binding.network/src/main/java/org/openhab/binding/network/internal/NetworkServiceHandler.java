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

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.openhab.core.thing.ChannelUID;
import org.openhab.core.thing.Thing;
import org.openhab.core.thing.ThingStatus;
import org.openhab.core.thing.binding.BaseThingHandler;
import org.openhab.core.types.Command;

/**
 * The {@link NetworkServiceHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 *
 * @author Marcel Kanter - Initial contribution
 */
public class NetworkServiceHandler extends BaseThingHandler {

    protected NetworkServiceDetectionTask detectionTask;
    protected ScheduledFuture<?> detectionFuture;

    public NetworkServiceHandler(Thing thing) {
        super(thing);

        this.detectionTask = new NetworkServiceDetectionTask(this);
        this.detectionFuture = null;
    }

    @Override
    public void initialize() {
        NetworkServiceConfiguration configuration = this.getConfigAs(NetworkServiceConfiguration.class);

        updateStatus(ThingStatus.UNKNOWN);

        if ((this.detectionFuture == null) && (this.detectionTask != null)) {
            this.detectionTask.hostname = configuration.hostname;
            this.detectionTask.port = configuration.port;

            this.detectionFuture = this.scheduler.scheduleWithFixedDelay(this.detectionTask, 1000,
                    configuration.refreshInterval, TimeUnit.MILLISECONDS);
        }
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
    }

    @Override
    public void handleRemoval() {
        this.detectionTask = null;

        super.handleRemoval();
    }

    @Override
    public void dispose() {
        if (this.detectionFuture != null) {
            this.detectionFuture.cancel(true);
            this.detectionFuture = null;
        }

        super.dispose();
    }
}
