/*
 * JCaptcha, the open source java framework for captcha definition and integration
 * Copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

/*
 * jcaptcha, the open source java framework for captcha definition and integration
 * copyright (c)  2007 jcaptcha.net. All Rights Reserved.
 * See the LICENSE.txt file distributed with this package.
 */

package com.octo.captcha.engine.image.fisheye;

import java.awt.image.ImageFilter;

import com.octo.captcha.component.image.backgroundgenerator.BackgroundGenerator;
import com.octo.captcha.component.image.backgroundgenerator.FileReaderRandomBackgroundGenerator;
import com.octo.captcha.component.image.deformation.ImageDeformation;
import com.octo.captcha.component.image.deformation.ImageDeformationByFilters;
import com.octo.captcha.engine.image.ListImageCaptchaEngine;
import com.octo.captcha.image.fisheye.FishEyeFactory;

/**
 * Produce fishEye from files. FishEye are done from sphere
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class SimpleFishEyeEngine extends ListImageCaptchaEngine {

    /**
     * this method should be implemented as folow : <ul> <li>First construct all the factories you want to initialize
     * the gimpy with</li> <li>then call the this.addFactoriy method for each factory</li> </ul>
     */
    protected void buildInitialFactories() {
        //build filters
        com.octo.jhlabs.image.SphereFilter sphere = new com.octo.jhlabs.image.SphereFilter();
        com.octo.jhlabs.image.RippleFilter ripple = new com.octo.jhlabs.image.RippleFilter();
        com.octo.jhlabs.image.TwirlFilter twirl = new com.octo.jhlabs.image.TwirlFilter();
        com.octo.jhlabs.image.WaterFilter water = new com.octo.jhlabs.image.WaterFilter();

        ripple.setWaveType(com.octo.jhlabs.image.RippleFilter.NOISE);
        ripple.setXAmplitude(10);
        ripple.setYAmplitude(10);
        ripple.setXWavelength(10);
        ripple.setYWavelength(10);
        ripple.setEdgeAction(com.octo.jhlabs.image.TransformFilter.CLAMP);

        water.setAmplitude(10);
 //       water.setAntialias(true);
        water.setWavelength(20);

        twirl.setAngle(4);

        sphere.setRefractionIndex(2);

        ImageDeformation rippleDef = new ImageDeformationByFilters(
                new ImageFilter[]{});
        ImageDeformation sphereDef = new ImageDeformationByFilters(
                new ImageFilter[]{});
        ImageDeformation waterDef = new ImageDeformationByFilters(
                new ImageFilter[]{});
        ImageDeformation twirlDef = new ImageDeformationByFilters(
                new ImageFilter[]{});

        //add background from files
        BackgroundGenerator generator = new FileReaderRandomBackgroundGenerator(
        		Integer.valueOf(250), Integer.valueOf(250),
                "./fisheyebackgrounds");
        addFactory(
                new FishEyeFactory(generator, sphereDef, Integer.valueOf(10),
                		Integer.valueOf(5)));
        addFactory(
                new FishEyeFactory(generator, rippleDef, Integer.valueOf(10),
                		Integer.valueOf(5)));
        addFactory(
                new FishEyeFactory(generator, waterDef, Integer.valueOf(10),
                		Integer.valueOf(5)));
        addFactory(
                new FishEyeFactory(generator, twirlDef, Integer.valueOf(10),
                		Integer.valueOf(5)));

    }
}
