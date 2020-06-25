package pasa.cbentley.layouter.swing.demo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import pasa.cbentley.layouter.src4.engine.Zer2DArea;
import pasa.cbentley.layouter.src4.tech.ITechLayout;
import pasa.cbentley.layouter.src4.tech.ITechSizer;
import pasa.cbentley.layouter.swing.ctx.SwingLayouterCtx;
import pasa.cbentley.layouter.swing.engine.JComponentLayoutable;

public class ResizerListener implements MouseMotionListener, MouseWheelListener, KeyListener, MouseListener {

   protected final JComponentLayoutable c;

   protected final SwingLayouterCtx     slc;

   public ResizerListener(SwingLayouterCtx slc, JComponentLayoutable c) {
      this.slc = slc;
      this.c = c;

   }

   public void keyTyped(KeyEvent e) {
      // TODO Auto-generated method stub

   }

   public void keyPressed(KeyEvent e) {
      // TODO Auto-generated method stub

   }

   public void keyReleased(KeyEvent e) {
      // TODO Auto-generated method stub

   }

   public void mouseWheelMoved(MouseWheelEvent e) {
      Zer2DArea area = c.getEngineSwing().getArea();
      if (e.getWheelRotation() == -1) {
         //up
         int v = area.getSizerH().get2(ITechSizer.SIZER_OFFSET_05_VALUE2);
         v += 1;
         area.getSizerH().set2(ITechSizer.SIZER_OFFSET_05_VALUE2, v);
      } else {
         //down
         int v = area.getSizerH().get2(ITechSizer.SIZER_OFFSET_05_VALUE2);
         v -= 1;
         area.getSizerH().set2(ITechSizer.SIZER_OFFSET_05_VALUE2, v);
      }
      c.layoutInvalidateSize();
      
      c.layoutUpdateSizeCheck();
      c.layoutUpdateDependencies(ITechLayout.DEPENDENCY_1_SIZE);
      
      //how to force revalidation of depend
   }

   public void mouseDragged(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   public void mouseMoved(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   public void mouseClicked(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   public void mousePressed(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub

   }

}
