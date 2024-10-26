/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.demo;

import pasa.cbentley.core.src4.ctx.IConfigU;
import pasa.cbentley.layouter.swing.ctx.LayouterSwingCtx;

public class LayouterDemoFactory {

   protected final LayouterSwingCtx slc;

   protected final IConfigU         configU;

   public LayouterDemoFactory(LayouterSwingCtx slc) {
      this(slc,null);
   }

   public LayouterDemoFactory(LayouterSwingCtx slc, IConfigU config) {
      this.slc = slc;
      this.configU = config;
   }

   public RunLayouterDemoSwingAbstract createNew(Class c) {
      if (c == RunLayouterDemoSwingButtons.class) {
         return new RunLayouterDemoSwingButtons(configU);

      } else if (c == RunLayouterDemoSwingButtonsDrag.class) {
         return new RunLayouterDemoSwingButtonsDrag(configU);

      } else if (c == RunLayouterDemoSwingButtonsDrag.class) {
         return new RunLayouterDemoSwingChain(configU);

      } else if (c == RunLayouterDemoSwingChain.class) {
         return new RunLayouterDemoSwingButtonsDrag(configU);

      } else if (c == RunLayouterDemoSwingForm.class) {
         return new RunLayouterDemoSwingForm(configU);

      } else if (c == RunLayouterDemoSwingGhostRule.class) {
         return new RunLayouterDemoSwingGhostRule(configU);
      } else {
         throw new IllegalArgumentException("Class " + c.getName() + " is not registered in the Factory");
      }
   }

}
