package pasa.cbentley.layouter.swing.demo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.layouter.src4.engine.Zer2DArea;
import pasa.cbentley.layouter.src4.tech.ITechLayout;
import pasa.cbentley.layouter.src4.tech.ITechSizer;
import pasa.cbentley.layouter.swing.ctx.ObjectSwingLayouter;
import pasa.cbentley.layouter.swing.ctx.SwingLayouterCtx;
import pasa.cbentley.layouter.swing.engine.JComponentLayoutable;
import pasa.cbentley.layouter.swing.interfaces.ILayoutableSwing;

/**
 * 
 * @author Charles Bentley
 *
 */
public class ResizerListener extends ObjectSwingLayouter implements MouseMotionListener, MouseWheelListener, KeyListener, MouseListener {

   protected final ILayoutableSwing c;

   protected boolean                isMouseOver = false;

   /**
    * 
    * @param slc
    * @param c The {@link JComponentLayoutable} that will be resized using the mouse
    */
   public ResizerListener(SwingLayouterCtx slc, ILayoutableSwing c) {
      super(slc);
      this.c = c;

   }

   /**
    * keytyped event returns 0 for code
    */
   public void keyTyped(KeyEvent e) {
      //#debug
      //toDLog().pFlow(slc.toSCD().d(e), this, ResizerListener.class, "keyTyped", LVL_05_FINE, true);
   }

   public void keyPressed(KeyEvent e) {
      //#debug
      toDLog().pFlow(slc.toSCD().d(e), this, ResizerListener.class, "keyPressed", LVL_05_FINE, true);
   
      Zer2DArea area = c.getEngineSwing().getArea();
      if (e.getKeyCode() == KeyEvent.VK_PLUS) {
         area.incrementSizerH(5);
      }
      if (e.getKeyCode() == KeyEvent.VK_MINUS) {
         area.decrementSizerH(5);
      }
   }

   public void keyReleased(KeyEvent e) {
      //#debug
      toDLog().pFlow(slc.toSCD().d(e), this, ResizerListener.class, "keyReleased", LVL_05_FINE, true);
   
   }

   public void mouseWheelMoved(MouseWheelEvent e) {
      Zer2DArea area = c.getEngineSwing().getArea();
      if (e.getWheelRotation() == -1) {
         //up
         area.incrementSizerH(1);
      } else {
         //down
         area.incrementSizerH(1);
         int v = area.getSizerH().get2(ITechSizer.SIZER_OFFSET_05_VALUE2);
         v -= 1;
         area.getSizerH().set2(ITechSizer.SIZER_OFFSET_05_VALUE2, v);
      }
      c.layoutInvalidateSize();

      c.layoutUpdateSizeCheck();
      c.layoutUpdateDependencies(ITechLayout.DEPENDENCY_1_SIZE);

      //how to force revalidation of depend
      //ask a repaint
      c.getComponent().repaint();
   }

   public void mouseDragged(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   public void mouseMoved(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   public void mouseClicked(MouseEvent e) {
      c.getComponent().requestFocus();
   }

   public void mousePressed(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   public void mouseEntered(MouseEvent e) {
      //#debug
      toDLog().pFlow("", this, ResizerListener.class, "mouseEntered", LVL_05_FINE, true);
      isMouseOver = true;
   }

   public void mouseExited(MouseEvent e) {
      //#debug
      toDLog().pFlow("", this, ResizerListener.class, "mouseExited", LVL_05_FINE, true);
      isMouseOver = false;
   }

   //#mdebug
   public void toString(Dctx dc) {
      dc.root(this, ResizerListener.class, "@line5");
      toStringPrivate(dc);
      super.toString(dc.sup());
   }

   private void toStringPrivate(Dctx dc) {
      dc.appendVarWithSpace("name", c.getComponent().getName());
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, ResizerListener.class);
      toStringPrivate(dc);
      super.toString1Line(dc.sup1Line());

   }

   //#enddebug

}
