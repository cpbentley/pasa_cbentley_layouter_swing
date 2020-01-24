/*
 * (c) 2018-2019 Charles-Philip Bentley
 * This code is licensed under MIT license (see LICENSE.txt for details)
 */
package pasa.cbentley.layouter.swing.widgets;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;

import javax.swing.JComponent;

import pasa.cbentley.byteobjects.src4.core.ByteObject;
import pasa.cbentley.core.src4.ctx.UCtx;
import pasa.cbentley.core.src4.logging.Dctx;
import pasa.cbentley.core.src4.logging.IDLog;
import pasa.cbentley.layouter.src4.engine.ByteObjectLayoutable;
import pasa.cbentley.layouter.src4.engine.LayoutDependenciesArray;
import pasa.cbentley.layouter.src4.engine.LayoutOperator;
import pasa.cbentley.layouter.src4.engine.LayoutableRect;
import pasa.cbentley.layouter.src4.engine.Zer2DArea;
import pasa.cbentley.layouter.src4.engine.Zer2DPozer;
import pasa.cbentley.layouter.src4.engine.Zer2DRect;
import pasa.cbentley.layouter.src4.engine.Zer2DSizer;
import pasa.cbentley.layouter.src4.interfaces.ILayoutDependencies;
import pasa.cbentley.layouter.src4.interfaces.ILayoutRequestListener;
import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
import pasa.cbentley.layouter.src4.tech.ITechLayout;
import pasa.cbentley.layouter.src4.tech.ITechPozer;
import pasa.cbentley.layouter.swing.ctx.SwingLayouterCtx;

public class LayoutableAdapter implements ILayoutable, ITechLayout {

   private Zer2DArea           area;

   private JComponent          c;

   private int                 cycleCounterH;

   private int                 cycleCounterW;

   private int                 cycleCounterX;

   private int                 cycleCounterY;

   private ILayoutDependencies dependencies;

   /**
    * the x value in rect is up to date
    */
   private boolean             isValidPositionX;

   private boolean             isValidPositionY;

   private boolean             isValidSizeH;

   private boolean             isValidSizeW;

   private boolean             isAppliedPositionX;

   private boolean             isAppliedPositionY;

   private boolean             isAppliedSizeH;

   private boolean             isAppliedSizeW;

   private int                 layoutID;

   private ILayoutable         parentLayout;

   private Zer2DRect           rect;

   private SwingLayouterCtx    slc;

   public LayoutableAdapter(SwingLayouterCtx slc, JComponent compo) {
      this(slc, compo, slc.getNewLayoutID());
   }

   public LayoutableAdapter(SwingLayouterCtx slc, JComponent compo, int layoutID) {
      this.slc = slc;
      this.c = compo;
      area = new Zer2DArea(slc);
      rect = new Zer2DRect(slc);

      this.layoutID = layoutID;
   }

   public void addDependency(ILayoutable layout, int type) {
      if (layout == null) {
         throw new NullPointerException();
      }
      if (dependencies == null) {
         dependencies = new LayoutDependenciesArray(slc);
      }
      dependencies.addDependency(layout, type);
   }

   /**
    * 
    * A {@link ILayoutable} that represents the area
    * 
    * <li>{@link ITechLayout#VIEW_STYLE_00_VIEW_FULL}
    * <li>{@link ITechLayout#VIEW_STYLE_01_VIEW_CONTENT_PAD_BORDER}
    * <li>{@link ITechLayout#VIEW_STYLE_02_VIEW_CONTENT_PAD}
    * <li>{@link ITechLayout#VIEW_STYLE_03_VIEW_CONTENT}
    * 
    * And 
    * 
    * <li>{@link ITechLayout#VIEW_STRUCT_00_ALL_VISIBLE}
    * 
    * @param subView
    * @return
    */
   public void buildLayoutableRectX(LayoutableRect copy, int subView, int subArea) {
      if (subView == ITechLayout.VIEW_STRUCT_00_ALL_VISIBLE) {
         //they are all the same
      }
   }

   public Zer2DArea getArea() {
      return area;
   }

   public JComponent getComponent() {
      return c;
   }

   public void repaintLayoutable() {

      c.repaint();
   }

   public ILayoutable[] getDependencies() {
      if (dependencies != null) {
         return dependencies.getDependencies();
      }
      return null;
   }

   public int getFontHeight() {
      Font font = c.getFont();
      FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
      return fontMetrics.getHeight();
   }

   public int getFontWidth() {
      Font font = c.getFont();
      FontMetrics fontMetrics = Toolkit.getDefaultToolkit().getFontMetrics(font);
      return fontMetrics.stringWidth("m");
   }

   public ILayoutable getLayoutableDelegate(ILayoutable source) {
      //don't have delegate here. let caller deal with null
      return null;
   }

   public ILayoutable getLayoutableID(int id) {
      //find id in pool otherwise null
      return null;
   }

   public ILayoutable getLayoutableNav(int dir) {
      //adapter does not know of such config
      return null;
   }

   public ILayoutable getLayoutableParent() {
      if (parentLayout == null) {
         Container parent = c.getParent();
         if (parent instanceof ILayoutable) {
            return (ILayoutable) parent;
         } else {
            throw new IllegalStateException("ILayoutable must be added to a ILayoutable container");
         }
      } else {
         return parentLayout;
      }
   }

   public ILayoutable getLayoutableViewContext() {
      //get the first view context in parent hierarchy
      //screen or layout? 
      return getLayoutableParent();
   }

   public ILayoutable getLayoutableViewPort() {
      //get the first scrollpane as parent
      return getLayoutableParent();
   }

   public int getLayoutID() {
      return layoutID;
   }

   public ILayoutRequestListener getLayoutRequestListener() {
      return null;
   }

   public ILayoutable getParentLayout() {
      return parentLayout;
   }

   public int getPozeX() {
      return rect.getX();
   }

   public int getPozeXComputed() {
      layoutUpdatePositionXCheck();
      return getPozeX();
   }

   public ILayoutable getLayoutableEtalon(int etalonType) {
      return null;
   }

   public int getPozeY() {
      return rect.getY();
   }

   /**
    * Returns the top y coordinate, compute it if invalid
    */
   public int getPozeYComputed() {
      layoutUpdatePositionYCheck();
      return getPozeY();
   }

   public int getSizeBorderHeight() {
      return getSizeDrawnHeight();
   }

   public int getSizeBorderWidth() {
      return getSizeDrawnWidth();
   }

   public int getSizeContentHeight() {
      return getSizeDrawnHeight();
   }

   public int getSizeContentWidth() {
      return getSizeDrawnWidth();
   }

   public int getSizeDrawnHeight() {
      //we read data from rect.. because underlying component is update later
      return rect.getH();
   }

   public int getSizeDrawnWidth() {
      return rect.getW();
   }

   public int getSizeEtalonH(ByteObject sizer) {
      return slc.getLayoutOperator().getSizeEtalonH(sizer, this);
   }

   public int getSizeEtalonW(ByteObject sizer) {
      return slc.getLayoutOperator().getSizeEtalonW(sizer, this);
   }

   public int getSizeFromDeletgateHeight() {
      return c.getHeight();
   }

   public int getSizeFromDeletgateWidth() {
      return c.getWidth();
   }

   public int getSizePaddingHeight() {
      return getSizeDrawnHeight();
   }

   public int getSizePaddingWidth() {
      return getSizeDrawnWidth();
   }

   public int getSizePreferredHeight() {
      //if rect TODO
      return c.getPreferredSize().height;
   }

   public int getSizePreferredWidth() {
      return c.getPreferredSize().width;
   }

   public int getSizeUnitHeight() {
      return getSizeDrawnHeight();
   }

   public int getSizeUnitWidth() {
      return getSizeDrawnWidth();
   }

   public int getWidthDelegate() {
      return getSizeFromDeletgateWidth();
   }

   public int getWidthDrawn() {
      return getSizeDrawnWidth();
   }

   public int getWidthFont() {
      return getFontWidth();
   }

   public int getWidthPreferred() {
      return getSizePreferredWidth();
   }

   public int getWidthUnit() {
      return getSizeUnitWidth();
   }

   private void layoutCheckLoopH() {
      if (cycleCounterH != 0) {
         //we have a loop
         String msg = "UI layout has a loop cycle while computing Height of " + this.toString1Line();
         //#debug
         toDLog().pNull(msg, this, LayoutableAdapter.class, "layoutCheckLoopH", LVL_10_SEVERE, false);
         throw new IllegalStateException("UI layout H has a loop cycle for " + this.toStringName());
      }
      cycleCounterH++;
   }

   private void layoutCheckLoopW() {
      if (cycleCounterW != 0) {
         if (isValidSizeW) {
            //we are calling layout when it is already valid
            throw new IllegalStateException("Calling layout Width on an already valid width. Consider using check");
         }
         //we have a loop
         String msg = "UI layout has a loop cycle while computing Width of " + this.toString1Line();
         //#debug
         toDLog().pNull(msg, this, LayoutableAdapter.class, "layoutCheckLoopW", LVL_10_SEVERE, false);
         throw new IllegalStateException("UI layout W has a loop cycle for " + this.toStringName());
      }
      cycleCounterW++;
   }

   public void layoutInvalidate() {
      isValidPositionX = false;
      isValidPositionY = false;
      isValidSizeW = false;
      isValidSizeH = false;
      isAppliedPositionX = false;
      isAppliedPositionY = false;
      isAppliedSizeH = false;
      isAppliedSizeW = false;
      cycleCounterH = 0;
      cycleCounterW = 0;
      cycleCounterX = 0;
      cycleCounterY = 0;
   }

   /**
    * 
    */
   public void layoutInvalidatePosition() {
      isValidPositionX = false;
      isValidPositionY = false;
      isAppliedPositionX = false;
      isAppliedPositionY = false;
      cycleCounterX = 0;
      cycleCounterY = 0;
      if (area.getPositionComputeFlagX() == COMPUTE_3_BOTH) {
         isValidSizeW = false;
         isAppliedSizeW = false;
         cycleCounterW = 0;
      }
      if (area.getPositionComputeFlagY() == COMPUTE_3_BOTH) {
         isValidSizeH = false;
         isAppliedSizeH = false;
         cycleCounterH = 0;
      }
   }

   public void layoutInvalidateSize() {
      isValidSizeW = false;
      isValidSizeH = false;
      isAppliedSizeH = false;
      isAppliedSizeW = false;
      cycleCounterH = 0;
      cycleCounterW = 0;
      //may invalidates the x,y left/top
      if (area.getPositionComputeFlagX() == COMPUTE_2_INVERSE) {
         isValidPositionX = false;
         isAppliedPositionX = false;
         cycleCounterX = 0;
      }
      if (area.getPositionComputeFlagY() == COMPUTE_2_INVERSE) {
         isValidPositionY = false;
         isAppliedPositionY = false;
         cycleCounterY = 0;
      }
   }

   public boolean layoutIsValidPosition() {
      return isValidPositionX && isValidPositionY;
   }

   public boolean layoutIsValidSize() {
      return isValidSizeH && isValidSizeW;
   }

   public void layoutUpdateDependencies(int flags) {
      if (dependencies != null) {
         dependencies.layoutUpdateDependencies(flags);
      }
   }

   /**
    * 
    */
   public void layoutUpdatePosition() {

      //#debug
      slc.getDebugBreaks().checkForBreakPointPos(this);

      layoutUpdatePositionX();
      layoutUpdatePositionY();

      c.setLocation(rect.getX(), rect.getY());

   }

   public void layoutUpdatePositionCheck() {
      if (layoutIsValidPosition()) {
         if (!isAppliedPositionX || !isAppliedPositionY) {
            applyRectToComponentPosition();
         }
         return;
      }

      //#debug
      slc.getDebugBreaks().checkForBreakPointPos(this);

      layoutUpdatePositionXCheck();
      layoutUpdatePositionYCheck();
      applyRectToComponentPosition();
   }

   private void applyRectToComponentSize() {
      int width = rect.getW();
      int height = rect.getH();
      Dimension preferredSize;
      int sizeComputeFlag = area.getSizeComputeFlag();
      if (sizeComputeFlag == COMPUTE_SIZE_0_NORMAL) {
         preferredSize = new Dimension(width, height);
      } else if (sizeComputeFlag == COMPUTE_SIZE_1_ONLY_W) {
         preferredSize = new Dimension(width, height);
      } else if (sizeComputeFlag == COMPUTE_SIZE_2_ONLY_H) {
         preferredSize = new Dimension(width, height);
      } else {
         preferredSize = new Dimension(rect.getPw(), rect.getPh());
      }
      c.setSize(width, height);
      c.setPreferredSize(preferredSize);
      isAppliedSizeH = true;
      isAppliedSizeW = true;
   }

   private void applyRectToComponentPosition() {
      c.setLocation(rect.getX(), rect.getY());
      isAppliedPositionX = true;
      isAppliedPositionY = true;
   }

   public void layoutUpdatePositionX() {
      int typeX = area.getPositionComputeFlagX();
      layoutUpdatePositionX(typeX);
   }

   private void layoutUpdatePositionX(int typeX) {
      //#debug
      slc.getDebugBreaks().checkForBreakPointPosX(this);

      LayoutOperator operator = slc.getLayoutOperator();
      if (typeX == COMPUTE_0_INVALID) {
         throw new IllegalStateException("Cannot compute X position without at least one X pozer");
      } else if (typeX == COMPUTE_1_NORMAL) {
         ByteObject pozerXStart = area.getPozerXStart();
         int x = operator.getPozXWidth(pozerXStart, this);
         rect.setX(x);
      } else if (typeX == COMPUTE_2_INVERSE) {
         ByteObject pozerXEnd = area.getPozerXEnd();
         int x2 = operator.getPozXWidth(pozerXEnd, this);
         //we need a valid computed width
         layoutUpdateSizeWCheck();
         //int x = x2 - rect.getW();
         rect.setX(x2);
      } else if (typeX == COMPUTE_3_BOTH) {
         ByteObject pozerXStart = area.getPozerXStart();
         ByteObject pozerXEnd = area.getPozerXEnd();
         int x = operator.getPozXPure(pozerXStart, this);
         int x2 = operator.getPozXPure(pozerXEnd, this);
         int width = x2 - x;
         rect.setX(x);
         rect.setW(width);
      }
      isValidPositionX = true;
   }

   public void layoutUpdatePositionXCheck() {
      if (isValidPositionX) {
         return;
      }
      layoutUpdatePositionX();
   }

   public void layoutUpdatePositionY() {
      int typeY = area.getPositionComputeFlagY();
      layoutUpdatePositionY(typeY);
   }

   private void layoutUpdatePositionY(int typeY) {
      //#debug
      slc.getDebugBreaks().checkForBreakPointPosY(this);

      LayoutOperator operator = slc.getLayoutOperator();
      if (typeY == COMPUTE_0_INVALID) {
         throw new IllegalStateException("Cannot compute Y position without at least one Y pozer");
      } else if (typeY == COMPUTE_1_NORMAL) {
         int y = operator.getPozYHeight(area.getPozerYTop(), this);
         rect.setY(y);
      } else if (typeY == COMPUTE_2_INVERSE) {
         int y2 = operator.getPozYHeight(area.getPozerYBot(), this);
         //we need to make sure the H is correct
         layoutUpdateSizeHCheck();
         //int y = y2 - rect.getH();
         rect.setY(y2);
      } else if (typeY == COMPUTE_3_BOTH) {
         int y = operator.getPozYPure(area.getPozerYTop(), this);
         int y2 = operator.getPozYPure(area.getPozerYBot(), this);
         int h = y2 - y;
         rect.setY(y);
         rect.setH(h);
      }
      isValidPositionY = true;
   }

   public void layoutUpdatePositionYCheck() {
      if (isValidPositionY) {
         return;
      }
      layoutUpdatePositionY();
   }

   //   public void layoutUpdateSize() {
   //
   //      //#debug
   //      slc.getDebugBreaks().checkForBreakPointSize(this);
   //
   //      //check if we have a size defined
   //      if (area.isValidSize()) {
   //         layoutUpdateSizeH();
   //         layoutUpdateSizeW();
   //         Dimension size = new Dimension(rect.getW(), rect.getH());
   //         c.setSize(size);
   //         c.setPreferredSize(size);
   //      } else {
   //         c.setSize(c.getPreferredSize());
   //         isValidSizeH = true;
   //         isValidSizeW = true;
   //      }
   //   }

   /**
    * 
    */
   public void layoutUpdateSizeCheck() {
      if (layoutIsValidSize()) {
         if (!isAppliedSizeW || !isAppliedSizeH) {
            applyRectToComponentSize();
         }
         return;
      }
      //#debug
      slc.getDebugBreaks().checkForBreakPointSize(this);

      //both methods will check the position flag and know what to do
      layoutUpdateSizeHCheck();
      layoutUpdateSizeWCheck();

      applyRectToComponentSize();

   }

   public void layoutUpdateSizeH() {
      //#debug
      slc.getDebugBreaks().checkForBreakPointSizeH(this);

      layoutCheckLoopH();
      LayoutOperator operator = slc.getLayoutOperator();
      int typeY = area.getPositionComputeFlagY();
      if (typeY == COMPUTE_3_BOTH) {
         ByteObject sizerH = area.getSizerH();
         if (sizerH != null) {
            //when in mode both and we do have a H sizer.. the sizer defines the preferred size
            int ph = operator.getPixelSizeH(sizerH, this);
            rect.setPh(ph);
         }
         isValidSizeH = true;
         layoutUpdatePositionYCheck();
      } else if (typeY == COMPUTE_1_NORMAL || typeY == COMPUTE_2_INVERSE) {
         ByteObject sizerH = area.getSizerH();
         if (sizerH == null) {
            generateSizerException(false);
         }
         int h = operator.getPixelSizeH(sizerH, this);
         rect.setH(h);
      } else if (typeY == COMPUTE_0_INVALID) {
         throw new IllegalStateException();
      }
      isValidSizeH = true;
   }

   private void generateSizerException(boolean isW) {
      String sizerStr = "SizerH";
      if (isW) {
         sizerStr = "SizerW";
      }
      //we have a loop
      String msg = sizerStr + " is not defined for" + this.toStringName();
      //#debug
      toDLog().pNull(msg, this, LayoutableAdapter.class, "generateSizerException", LVL_10_SEVERE, false);
      throw new IllegalStateException(msg);
   }

   public void layoutUpdateSizeHCheck() {
      if (isValidSizeH) {
         return;
      }
      layoutUpdateSizeH();
   }

   private void layoutUpdateSizeW() {
      //#debug
      slc.getDebugBreaks().checkForBreakPointSizeW(this);

      //check loop
      layoutCheckLoopW();

      LayoutOperator operator = slc.getLayoutOperator();
      int typeX = area.getPositionComputeFlagX();
      if (typeX == COMPUTE_3_BOTH) {
         //if we have a w sizer with 2 pozers.. that's pref size
         ByteObject sizerW = area.getSizerW();
         if (sizerW != null) {
            //pref size
            int pw = operator.getPixelSizeW(sizerW, this);
            rect.setPw(pw);
         }
         isValidSizeW = true;
         layoutUpdatePositionXCheck();
      } else if (typeX == COMPUTE_1_NORMAL || typeX == COMPUTE_2_INVERSE) {
         ByteObject sizerW = area.getSizerW();
         if (sizerW == null) {
            generateSizerException(true);
         }
         int w = operator.getPixelSizeW(sizerW, this);
         rect.setW(w);
      } else if (typeX == COMPUTE_0_INVALID) {
         throw new IllegalStateException();
      }
      isValidSizeW = true;
   }

   public void layoutUpdateSizeWCheck() {
      if (isValidSizeW) {
         return;
      }
      layoutUpdateSizeW();
   }

   private void layPoz(ILayoutable lay, ByteObject pozer) {
      pozer.set1(ITechPozer.POS_OFFSET_02_ETALON1, ITechPozer.POS_ETALON_6_LAYOUTABLE);
      ByteObjectLayoutable boLayoutable = new ByteObjectLayoutable(slc.getBOC(), lay);
      pozer.addByteObject(boLayoutable);
   }

   public void layPozBotToBotOf(ILayoutable lay) {
      ByteObject pozerY = slc.getPozerFactory().getPozerBotToBot();
      layPozYBot(lay, pozerY);
   }

   public void layPozBotToBotOfParent() {
      layPozBotToBotOf(null);
   }

   public void layPozBotToMidOf(ILayoutable lay) {
      //the size is the distance between 2 pozers, one of which is implicit
      ByteObject pozerY = slc.getPozerFactory().getPozerBotToMid();
      layPozYBot(lay, pozerY);
   }

   public void layPozBotToMidOfParent() {
      layPozBotToMidOf(null);
   }

   public void layPozBotToTopOf(ILayoutable lay) {
      ByteObject pozerY = slc.getPozerFactory().getPozerBotToTop();
      layPozYBot(lay, pozerY);
   }

   public void layPozBotToTopOfParent() {
      layPozBotToTopOf(null);
   }

   public void layPozCorner1ToCorner1Of(ILayoutable lay) {
      layPozTopToTopOf(lay);
      layPozStartToStartOf(lay);
   }

   public void layPozCorner1ToCorner1OfParent() {
      layPozCorner1ToCorner1Of(null);
   }

   public void layPozCorner1ToCorner2Of(ILayoutable lay) {
      layPozTopToTopOf(lay);
      layPozStartToEndOf(lay);
   }

   public void layPozCorner1ToCorner2OfParent() {
      layPozCorner1ToCorner2Of(null);
   }

   public void layPozCorner1ToCorner3Of(ILayoutable lay) {
      layPozTopToBotOf(lay);
      layPozStartToEndOf(lay);
   }

   public void layPozCorner1ToCorner3OfParent() {
      layPozCorner1ToCorner3Of(null);
   }

   public void layPozCorner1ToCorner4Of(ILayoutable lay) {
      layPozTopToBotOf(lay);
      layPozStartToStartOf(lay);
   }

   public void layPozCorner1ToCorner4OfParent() {
      layPozCorner1ToCorner4Of(null);
   }

   public void layPozCorner3ToCorner1Of(ILayoutable lay) {
      layPozBotToTopOf(lay);
      layPozEndToStartOf(lay);
   }

   public void layPozCorner3ToCorner1OfParent() {
      layPozCorner3ToCorner1Of(null);
   }

   public void layPozCorner3ToCorner2Of(ILayoutable lay) {
      layPozBotToTopOf(lay);
      layPozEndToEndOf(lay);
   }

   public void layPozCorner3ToCorner2OfParent() {
      layPozCorner3ToCorner2Of(null);
   }

   public void layPozCorner3ToCorner3Of(ILayoutable lay) {
      layPozBotToBotOf(lay);
      layPozEndToEndOf(lay);
   }

   public void layPozCorner3ToCorner3OfParent() {
      layPozCorner3ToCorner3Of(null);
   }

   public void layPozCorner3ToCorner4Of(ILayoutable lay) {
      layPozBotToBotOf(lay);
      layPozEndToStartOf(lay);
   }

   public void layPozCorner3ToCorner4OfParent() {
      layPozCorner3ToCorner4Of(null);
   }

   /**
    * Defines 2 pozers
    */
   public void layPozCornerTopLeftOfParent() {
      ByteObject pozerY = slc.getPozerFactory().getPozerTopToTop();
      setPozerYTop(pozerY);

      ByteObject pozerX = slc.getPozerFactory().getPozerStartToStart();
      setPozerXStart(pozerX);
   }

   public void layPozEndToEndOf(ILayoutable lay) {
      ByteObject pozerX = slc.getPozerFactory().getPozerEndToEnd();
      layPozXEnd(lay, pozerX);
   }

   public void layPozEndToEndParent() {
      layPozEndToEndOf(null);
   }

   public void layPozStartToCenterOf(ILayoutable lay) {
      ByteObject pozerX = slc.getPozerFactory().getPozerStartToCenter();
      layPozXEnd(lay, pozerX);
   }

   public void layPozEndToCenterOf(ILayoutable lay) {
      ByteObject pozerX = slc.getPozerFactory().getPozerEndToCenter();
      layPozXEnd(lay, pozerX);
   }

   public void layPozEndToStartOf(ILayoutable lay) {
      ByteObject pozerX = slc.getPozerFactory().getPozerEndToStart();
      layPozXEnd(lay, pozerX);
   }

   public void layPozEndToStartOfParent() {
      layPozEndToStartOf(null);
   }

   public void layPozMidXToEndOf(ILayoutable lay) {
      ByteObject pozerX = slc.getPozerFactory().getPozerMidToEnd();
      layPozX(lay, pozerX);
   }

   public void layPozMidXToMidOf(ILayoutable lay) {
      ByteObject pozerX = slc.getPozerFactory().getPozerCenterToCenter();
      layPozX(lay, pozerX);
   }

   public void layPozMidXToMidOfParent() {
      layPozMidXToMidOf(null);
   }

   public void layPozMidXToStartOf(ILayoutable lay) {
      ByteObject pozerX = slc.getPozerFactory().getPozerMidToStart();
      layPozX(lay, pozerX);
   }

   public void layPozMidYToBotOf(ILayoutable lay) {
      ByteObject pozerX = slc.getPozerFactory().getPozerMidToEnd();
      layPozY(lay, pozerX);
   }

   public void layPozMidYToTopOf(ILayoutable lay) {
      ByteObject pozerY = slc.getPozerFactory().getPozerMidToTop();
      layPozY(lay, pozerY);
   }

   private void layPozParent(ByteObject pozer) {
      pozer.set1(ITechPozer.POS_OFFSET_02_ETALON1, ITechPozer.POS_ETALON_1_PARENT);
   }

   public void layPozStartToEndOf(ILayoutable lay) {
      ByteObject pozerX = slc.getPozerFactory().getPozerStartToEnd();
      layPozXStart(lay, pozerX);
   }

   public void layPozStartToEndParent() {
      layPozStartToEndOf(null);
   }

   public void layPozStartToStartOf(ILayoutable lay) {
      ByteObject pozerX = slc.getPozerFactory().getPozerStartToStart();
      layPozXStart(lay, pozerX);
   }

   public void layPozStartToStartOfParent() {
      layPozStartToStartOf(null);
   }

   public void layPozTopToBotOf(ILayoutable lay) {
      ByteObject pozerY = slc.getPozerFactory().getPozerTopToBottom();
      layPozYTop(lay, pozerY);
   }

   public void layPozTopToBotOfParent() {
      layPozTopToBotOf(null);
   }

   public void layPozTopToMidOf(ILayoutable lay) {
      //define the y pos
      ByteObject pozerY = slc.getPozerFactory().getPozerTopToMid();
      layPozYTop(lay, pozerY);
   }

   public void layPozTopToTopOf(ILayoutable lay) {
      ByteObject pozerY = slc.getPozerFactory().getPozerTopToTop();
      layPozYTop(lay, pozerY);
   }

   public void layPozTopToTopOfParent() {
      layPozTopToTopOf(null);
   }

   /**
    * 
    * @param lay
    * @param pozerX
    */
   private void layPozX(ILayoutable lay, ByteObject pozerX) {
      layPozXStart(lay, pozerX);
   }

   private void layPozXEnd(ILayoutable lay, ByteObject pozerX) {
      if (lay == null) {
         //use parent
         layPozParent(pozerX);
      } else {
         layPoz(lay, pozerX);
      }
      area.setPozerXEnd(pozerX);
   }

   private void layPozXStart(ILayoutable lay, ByteObject pozerX) {
      if (lay == null) {
         //use parent
         layPozParent(pozerX);
      } else {
         layPoz(lay, pozerX);
      }
      area.setPozerXStart(pozerX);
   }

   private void layPozY(ILayoutable lay, ByteObject pozerX) {
      layPozYTop(lay, pozerX);
   }

   private void layPozYBot(ILayoutable lay, ByteObject pozerY) {
      if (lay == null) {
         //use parent
         layPozParent(pozerY);
      } else {
         layPoz(lay, pozerY);
      }
      area.setPozerYBot(pozerY);
   }

   private void layPozYTop(ILayoutable lay, ByteObject pozerY) {
      if (lay == null) {
         //use parent
         layPozParent(pozerY);
      } else {
         layPoz(lay, pozerY);
      }
      area.setPozerYTop(pozerY);
   }

   public void laySizePreferred() {
      area.setSizerH(slc.getFactorySizer().getSizerPrefLazy());
      area.setSizerW(slc.getFactorySizer().getSizerPrefLazy());
   }

   public void removeDependency(ILayoutable layout, int type) {
      dependencies.removeDependency(layout, type);
   }

   public void setArea(Zer2DArea area) {
      this.area = area;
   }

   public void setParentLayout(ILayoutable parentLayout) {
      this.parentLayout = parentLayout;
   }

   public void setPozerCorner1(Zer2DPozer pozerCorner1) {
      setPozerXStart(pozerCorner1.getPozerX());
      setPozerYTop(pozerCorner1.getPozerY());
   }

   /**
    * Link the top right corner to
    * @param pozerX
    * @param pozerY
    */
   public void setPozerCorner2(ByteObject pozerX, ByteObject pozerY) {
      setPozerXEnd(pozerX);
      setPozerYTop(pozerY);
   }

   public void setPozerCorner2(Zer2DPozer pozerCorner2) {
      setPozerXEnd(pozerCorner2.getPozerX());
      setPozerYTop(pozerCorner2.getPozerY());
   }

   public void setPozerCorner3(Zer2DPozer pozerCorner3) {
      setPozerXEnd(pozerCorner3.getPozerX());
      setPozerYBot(pozerCorner3.getPozerY());
   }

   public void setPozerCorner4(Zer2DPozer pozerCorner4) {
      setPozerXStart(pozerCorner4.getPozerX());
      setPozerYBot(pozerCorner4.getPozerY());
   }

   public void setPozerXEnd(ByteObject pozerX) {
      area.setPozerXEnd(pozerX);
   }

   public void setPozerXStart(ByteObject pozerX) {
      area.setPozerXStart(pozerX);
   }

   public void setPozerYBot(ByteObject pozerY) {
      area.setPozerYBot(pozerY);
   }

   public void setPozerYTop(ByteObject pozerY) {
      area.setPozerYTop(pozerY);
   }

   public void setSizer(Zer2DSizer sizer) {
      this.area.setSizer(sizer);
   }

   public void setSizerH(ByteObject sizerH) {
      area.setSizerH(sizerH);
   }

   public void setSizerW(ByteObject sizerW) {
      area.setSizerW(sizerW);
   }

   public int getSizeMaxHeight(ILayoutable layoutable) {
      //we don't do checks but we could
      return rect.getH();
   }

   public int getSizeMaxWidth(ILayoutable layoutable) {
      //we don't do checks but we could
      return rect.getW();
   }

   //#mdebug
   public IDLog toDLog() {
      return toStringGetUCtx().toDLog();
   }

   public String toString() {
      return Dctx.toString(this);
   }

   public void toString(Dctx dc) {
      dc.root(this, "LayoutableAdapter");
      toStringPrivate(dc);

      //we want nice message
      area.toString(dc.nLevel(), this);

      dc.nlLvl(rect, "Rect");
      dc.nlLvlO(c, "JComponent");
      dc.nlLvl(parentLayout, "ParentLayout");
      dc.nlLvl(dependencies, "Depedencies");

      slc.d(c, dc.nLevel());

   }

   public String toString1Line() {
      return Dctx.toString1Line(this);
   }

   public void toString1Line(Dctx dc) {
      dc.root1Line(this, "LayoutableAdapter");
      toStringPrivate(dc);
   }

   public UCtx toStringGetUCtx() {
      return slc.getUCtx();
   }

   public String toStringName() {
      return c.getName();
   }

   private void toStringPrivate(Dctx dc) {
      if (c != null) {
         dc.appendWithSpaceIfNotNull('[', c.getName(), ']');
      }
      dc.appendVarWithSpace("isValidSizeW", isValidSizeW);
      dc.appendVarWithSpace("isValidSizeH", isValidSizeH);
      dc.appendVarWithSpace("isValidPositionX", isValidPositionX);
      dc.appendVarWithSpace("isValidPositionY", isValidPositionY);
      dc.appendVarWithSpace("cycleCounterW", cycleCounterW);
      dc.appendVarWithSpace("cycleCounterH", cycleCounterH);
      dc.appendVarWithSpace("cycleCounterX", cycleCounterX);
      dc.appendVarWithSpace("cycleCounterY", cycleCounterY);
      dc.appendVarWithSpace("layoutID", layoutID);
   }

   //#enddebug

}
