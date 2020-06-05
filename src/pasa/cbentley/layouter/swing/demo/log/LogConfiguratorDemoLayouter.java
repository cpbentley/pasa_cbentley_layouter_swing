/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.demo.log;

import pasa.cbentley.core.src4.logging.IDLogConfig;
import pasa.cbentley.core.src4.logging.ILogConfigurator;
import pasa.cbentley.core.src4.logging.ITechLvl;
import pasa.cbentley.core.src4.logging.ITechTags;
import pasa.cbentley.swing.ctx.SwingCtx;

public class LogConfiguratorDemoLayouter implements ILogConfigurator {


   public void apply(IDLogConfig log) {
      log.setLevelGlobal(ITechLvl.LVL_03_FINEST);

      log.setFlagTag(ITechTags.FLAG_18_PRINT_MEMORY, false);
      log.setFlagTag(ITechTags.FLAG_01_PRINT_ALWAYS, true);
      log.setFlagTag(ITechTags.FLAG_09_PRINT_FLOW, true);
      log.setFlagTag(ITechTags.FLAG_25_PRINT_NULL, true);
      log.setFlagTag(ITechTags.FLAG_20_PRINT_INIT, true);

      log.setClassNegative(SwingCtx.class, false);
   }

}
