/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.widgets;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JComponent;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.core.src4.logging.IStringable;
import pasa.cbentley.layouter.swing.ctx.SwingLayouterCtx;

public class KeyListenerRoot implements KeyListener, IStringable {

   private SwingLayouterCtx slc;

   private IStringable      comp;

   public KeyListenerRoot(SwingLayouterCtx slc, IStringable comp) {
      this.slc = slc;
      this.comp = comp;

   }

   public void keyTyped(KeyEvent e) {

   }

   public void keyPressed(KeyEvent e) {
      //#debug
      toDLog().pFlow("=" + e.getKeyCode(), null, KeyListenerRoot.class, "keyPressed", LVL_05_FINE, true);

      if (e.getKeyCode() == KeyEvent.VK_F4) {
         toDLog().pAlways("F4 Cmd Debug", comp, KeyListenerRoot.class, "keyPressed");
      }
   }

   public void keyReleased(KeyEvent e) {

   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "KeyListenerRoot");
      toStringPrivate(dc);
   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   private void toStringPrivate(Dctx dc) {

   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "KeyListenerRoot");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return slc.getUCtx();
   }

   //#enddebug

}
