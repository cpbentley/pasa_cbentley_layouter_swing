/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.ctx;

import pasa.cbentley.core.src4.ctx.ConfigAbstract;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.swing.ctx.IConfigSwing;

public class ConfigSwingLayouterDefault extends ConfigAbstract implements IConfigLayouterSwing {

   public ConfigSwingLayouterDefault(UCtx uc) {
      super(uc);
   }

   public boolean isSizePrefferedDefault() {
      return false;
   }

}
