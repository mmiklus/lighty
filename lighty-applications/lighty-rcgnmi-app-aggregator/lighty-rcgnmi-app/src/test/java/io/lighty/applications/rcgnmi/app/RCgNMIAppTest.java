/*
 * Copyright (c) 2021 PANTHEON.tech s.r.o. All Rights Reserved.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 which accompanies this distribution,
 * and is available at https://www.eclipse.org/legal/epl-v10.html
 */
package io.lighty.applications.rcgnmi.app;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import io.lighty.applications.rcgnmi.module.RcGnmiAppModule;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class RCgNMIAppTest {

    @Test
    public void testStartWithDefaultConfiguration() {
        final RCgNMIApp app = Mockito.spy(new RCgNMIApp());
        final RcGnmiAppModule appModule = Mockito.mock(RcGnmiAppModule.class);
        doReturn(true).when(appModule).initModules();
        doReturn(true).when(appModule).close();
        doReturn(appModule).when(app).createRgnmiAppModule(any(), any(), any());
        app.start(new String[]{});
        verify(app, times(1)).createRgnmiAppModule(any(), any(), any());
    }

    @Test
    public void testStartWithConfigFile() {
        final RCgNMIApp app = Mockito.spy(new RCgNMIApp());
        final RcGnmiAppModule appModule = Mockito.mock(RcGnmiAppModule.class);
        doReturn(true).when(appModule).initModules();
        doReturn(true).when(appModule).close();
        doReturn(appModule).when(app).createRgnmiAppModule(any(), any(), any());
        app.start(new String[]{"-c", "src/main/resources/example-config/example_config.json"});
        verify(app, times(1)).createRgnmiAppModule(any(), any(), any());
    }

    @Test
    public void testStartWithConfigFileNoSuchFile() {
        final RCgNMIApp app = Mockito.spy(new RCgNMIApp());
        app.start(new String[]{"-c", "no_config.json"});
        verify(app, never()).createRgnmiAppModule(any(), any(), any());
    }
}
