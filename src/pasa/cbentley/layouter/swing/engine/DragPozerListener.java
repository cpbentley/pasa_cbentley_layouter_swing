/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.engine;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.SwingUtilities;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.layouter.src4.engine.Zer2DArea;
import pasa.cbentley.layouter.src4.tech.ITechPozer;
import pasa.cbentley.layouter.swing.ctx.SwingLayouterCtx;
import pasa.cbentley.layouter.swing.widgets.BLPanel;
import pasa.cbentley.layouter.swing.widgets.LayoutableAdapter;

/**
 * Move the object when pressed over, ignores current pozers.. mouse wheel reset layoutable
 * to original pozers.
 * 
 * @author Charles Bentley
 *
 */
public class DragPozerListener implements MouseListener, MouseMotionListener, MouseWheelListener {

   protected final SwingLayouterCtx slc;

   private Zer2DArea                areaOriginal;

   private Zer2DArea                areaPressed;

   private Zer2DArea                areaDragged;

   private LayoutableAdapter        layoutable;

   private boolean                  isPressed;

   private int                      pressedX;

   private int                      pressedY;

   private BLPanel                  root;

   public DragPozerListener(SwingLayouterCtx slc, LayoutableAdapter layoutable, BLPanel root) {
      this.slc = slc;
      this.root = root;
      if (layoutable == null) {
         throw new NullPointerException();
      }
      this.areaOriginal = layoutable.getArea();
      areaDragged = areaOriginal.cloneMe();
      this.layoutable = layoutable;
   }

   public void mouseWheelMoved(MouseWheelEvent e) {
      areaDragged = areaOriginal.cloneMe();
      //set the area to the layout table
      layoutable.layoutUpdatePositionCheck();
   }

   public void mouseDragged(MouseEvent e) {
      if (!isPressed) {
         throw new IllegalStateException();
      }

      Point p = SwingUtilities.convertPoint(layoutable.getComponent(), e.getPoint(), root);

      int vectorX = pressedX - p.x;
      int vectorY = pressedY - p.y;

      //modify area
      ByteObject pozerXStart = areaPressed.getPozerXStart();
      int pressedLayX = pozerXStart.get4(ITechPozer.POS_OFFSET_03_ANCHOR_ETALON_POINT_VALUE4);

      areaDragged.getPozerXStart().set4(ITechPozer.POS_OFFSET_03_ANCHOR_ETALON_POINT_VALUE4, pressedLayX - vectorX);

      ByteObject pozerYTop = areaPressed.getPozerYTop();
      int pressedLayY = pozerYTop.get4(ITechPozer.POS_OFFSET_03_ANCHOR_ETALON_POINT_VALUE4);
      areaDragged.getPozerYTop().set4(ITechPozer.POS_OFFSET_03_ANCHOR_ETALON_POINT_VALUE4, pressedLayY - vectorY);

      layoutable.setArea(areaDragged);

      //what if the drag modifies the size as well?
      //layoutable.layoutUpdatePositionCheck();

      //update dependencies
      //layoutable.layoutUpdateDependencies(ITechLayout.DEPENDENCY_2_POZE);

      //repaint or relayout? what about Z order?
      //ask blpanel to only remove and add again without invalidating layouts
      root.layoutSoftValidation();
      //layoutable.repaintLayoutable();

   }

   public void mouseMoved(MouseEvent e) {

   }

   public void mouseClicked(MouseEvent e) {
      // TODO Auto-generated method stub

   }

   public void mousePressed(MouseEvent e) {
      isPressed = true;
      //we want to keep the same width and height parameters
      areaPressed = layoutable.getArea().cloneMe();

      //his position is not fixed point

      Point p = SwingUtilities.convertPoint(layoutable.getComponent(), e.getPoint(), root);

      //coordinate is relative to component!.. w
      pressedX = p.x;
      pressedY = p.y;

      int bx = e.getX();
      int by = e.getY();
      int currentX = p.x - bx;
      int currentY = p.y - by;

      ByteObject pozerXStart = slc.getPozerFactory().getPozerAtTopStartForPixel(currentX);
      ByteObject pozerYTop = slc.getPozerFactory().getPozerAtTopStartForPixel(currentY);

      areaPressed.setPozerXStart(pozerXStart);
      areaPressed.setPozerXEnd(null); //remove any constraints
      areaPressed.setPozerYTop(pozerYTop);
      areaPressed.setPozerYBot(null);

      areaDragged = areaPressed.cloneMe();

   }

   public void mouseReleased(MouseEvent e) {
      isPressed = false;
      areaDragged = null;
   }

   public void mouseEntered(MouseEvent e) {
   }

   public void mouseExited(MouseEvent e) {
   }
}
