/*
 * (c) 2018-2020 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.ctx;

import javax.swing.JComponent;

import pasa.cbentley.byteobjects.src4.ctx.BOCtx;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src5.ctx.C5Ctx;
import pasa.cbentley.core.swing.stringables.SwingCoreDebug;
import pasa.cbentley.layouter.src4.ctx.LayouterCtx;
import pasa.cbentley.layouter.swing.engine.LayoutableAdapterForJComponent;
import pasa.cbentley.swing.ctx.SwingCtx;
import pasa.cbentley.swing.logging.SwingDebug;

/**
 * Ctx for using "The Layouter" with the Swing framework. 
 *
 * @author Charles Bentley
 *
 */
public class LayouterSwingCtx extends LayouterCtx {

   protected final C5Ctx          c5;

   protected IConfigLayouterSwing config;

   protected final SwingCtx       sc;

   public LayouterSwingCtx(BOCtx boc, SwingCtx sc) {
      this(null, boc, sc);
   }

   /**
    * Master contructor. So it reuses in case of nu
    * @param config
    * @param boc cannot be null
    * @param sc cannot be null
    */
   public LayouterSwingCtx(IConfigLayouterSwing config, BOCtx boc, SwingCtx sc) {
      super((config == null) ? new ConfigSwingLayouterDefault(boc.getUC()) : config, boc);
      this.config = config;
      this.c5 = sc.getC5();
      this.sc = sc;
   }

   /**
    * 
    * @param c
    * @return
    */
   public LayoutableAdapterForJComponent createFor(JComponent c) {
      return new LayoutableAdapterForJComponent(this, c);
   }

   public C5Ctx getC5() {
      return c5;
   }

   public SwingCtx getSwingCtx() {
      return sc;
   }

   public SwingCoreDebug toSCD() {
      return sc.toSCD();
   }

   public SwingDebug toSD() {
      return sc.toSD();
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, "SwingLayouterCtx");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "SwingLayouterCtx");
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());
   }

   private void toStringPrivate(Dctx dc) {

   }

   //#enddebug

}
