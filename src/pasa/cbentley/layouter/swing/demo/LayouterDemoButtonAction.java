/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.demo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import pasa.cbentley.layouter.swing.ctx.SwingLayouterCtx;

public class LayouterDemoButtonAction implements ActionListener {

   protected final SwingLayouterCtx    slc;

   protected final LayouterDemoFactory fac;

   protected final Class c;

   public LayouterDemoButtonAction(SwingLayouterCtx slc, LayouterDemoFactory fac, Class c) {
      this.slc = slc;
      this.fac = fac;
      this.c = c;
   }

   public void actionPerformed(ActionEvent e) {
      RunLayouterDemoSwingAbstract run = fac.createNew(c);
      run.run();
   }

}
