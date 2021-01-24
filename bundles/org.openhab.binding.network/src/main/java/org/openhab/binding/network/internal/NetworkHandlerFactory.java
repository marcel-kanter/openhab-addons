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

import static org.openhab.binding.network.internal.NetworkBindingConstants.*;

import java.util.Dictionary;
import java.util.Set;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.core.thing.Thing;
import org.openhab.core.thing.ThingTypeUID;
import org.openhab.core.thing.binding.BaseThingHandlerFactory;
import org.openhab.core.thing.binding.ThingHandler;
import org.openhab.core.thing.binding.ThingHandlerFactory;
import org.osgi.service.component.ComponentContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;

/**
 * The {@link NetworkHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Marcel Kanter - Initial contribution
 */
@NonNullByDefault
@Component(configurationPid = "binding.network", service = ThingHandlerFactory.class)
public class NetworkHandlerFactory extends BaseThingHandlerFactory {

    private static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Set.of(THING_TYPE_DEVICE, THING_TYPE_SERVICE);

    private NetworkBindingConfiguration configuration = new NetworkBindingConfiguration();

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Activate
    @Override
    protected void activate(ComponentContext componentContext) {
        super.activate(componentContext);
        this.updateConfiguration(componentContext.getProperties());
    }

    @Modified
    protected void modified(ComponentContext componentContext) {
        this.updateConfiguration(componentContext.getProperties());
    }

    @Override
    protected @Nullable ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (thingTypeUID.equals(THING_TYPE_DEVICE)) {
            return new NetworkDeviceHandler(thing);
        }
        if (thingTypeUID.equals(THING_TYPE_SERVICE)) {
            return new NetworkServiceHandler(thing);
        }

        return null;
    }

    private void updateConfiguration(Dictionary<String, Object> dictionary) {
        try {
            this.configuration.arpingPath = (String) dictionary.get("arpingPath");
        } catch (Exception e) {
        }
    }
}
