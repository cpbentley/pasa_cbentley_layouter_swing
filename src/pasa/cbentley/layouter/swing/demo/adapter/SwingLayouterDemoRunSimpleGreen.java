///*
// * (c) 2018-2019 Charles-Philip Bentley
// * This code is licensed under MIT license (see LICENSE.txt for details)
// */
//package pasa.cbentley.layouter.swing.demo.adapter;
//
//import java.awt.Color;
//
//import javax.swing.JButton;
//import javax.swing.JScrollPane;
//import javax.swing.JTextPane;
//import javax.swing.ScrollPaneConstants;
//import javax.swing.text.AttributeSet;
//import javax.swing.text.SimpleAttributeSet;
//import javax.swing.text.StyleConstants;
//import javax.swing.text.StyleContext;
//
//import pasa.cbentley.byteobjects.src4.core.ByteObject;
//import pasa.cbentley.byteobjects.src4.ctx.BOCtx;
//import pasa.cbentley.core.src4.ctx.UCtx;
//import pasa.cbentley.core.src4.logging.BaseDLogger;
//import pasa.cbentley.core.src4.logging.IDLogConfig;
//import pasa.cbentley.core.src4.logging.IStringable;
//import pasa.cbentley.core.src4.logging.ITechConfig;
//import pasa.cbentley.core.src4.logging.ITechLvl;
//import pasa.cbentley.core.src5.ctx.C5Ctx;
//import pasa.cbentley.layouter.src4.engine.LayoutWillListenerAdapter;
//import pasa.cbentley.layouter.src4.engine.PozerFactory;
//import pasa.cbentley.layouter.src4.engine.Zer2DPozer;
//import pasa.cbentley.layouter.src4.interfaces.ILayoutWillListener;
//import pasa.cbentley.layouter.src4.interfaces.ILayoutable;
//import pasa.cbentley.layouter.src4.tech.ITechLayout;
//import pasa.cbentley.layouter.swing.ctx.SwingLayouterCtx;
//import pasa.cbentley.layouter.swing.engine.DragPozerListener;
//import pasa.cbentley.layouter.swing.widgets.adapter.JComponentLayouter;
//import pasa.cbentley.layouter.swing.widgets.adapter.JFrameLayouter;
//import pasa.cbentley.layouter.swing.widgets.adapter.JPanelLayouter;
//import pasa.cbentley.layouter.swing.widgets.adapter.LayoutableAdapter;
//
//public class SwingLayouterDemoRunSimpleGreen {
//
//   public static void main(String[] args) {
//
//      UCtx uc = new UCtx();
//      C5Ctx c5 = new C5Ctx(uc);
//      BaseDLogger loggerFirst = (BaseDLogger) uc.toDLog();
//      IDLogConfig config = loggerFirst.getDefault().getConfig();
//      config.setLevelGlobal(ITechLvl.LVL_03_FINEST);
//      config.setFlagPrint(ITechConfig.MASTER_FLAG_02_OPEN_ALL_PRINT, true);
//
//      BOCtx boc = new BOCtx(uc);
//      SwingLayouterCtx slc = new SwingLayouterCtx(uc, boc, c5);
//
//      ByteObject sizer45PercentParent = slc.getSizerFactory().getSizerRatio100(ITechLayout.ETALON_4_PARENT, 45);
//      ByteObject sizer40PercentParent = slc.getSizerFactory().getSizerRatio100(ITechLayout.ETALON_4_PARENT, 40);
//      ByteObject sizer30PercentParent = slc.getSizerFactory().getSizerRatio100(ITechLayout.ETALON_4_PARENT, 30);
//
//      ByteObject sizer10PercentParent = slc.getSizerFactory().getSizerRatio100(ITechLayout.ETALON_4_PARENT, 10);
//      ByteObject sizer20PercentParent = slc.getSizerFactory().getSizerRatio100(ITechLayout.ETALON_4_PARENT, 10);
//
//      
//      PozerFactory pozerFac = slc.getFactoryPozer();
//
//      ByteObject centerToCenter = pozerFac.getPozerCenterToCenter();
//      
//      ByteObject botToBot = pozerFac.getPozerBotToBot();
//      ByteObject topToTop = pozerFac.getPozerTopToTop();
//      ByteObject leftToLeft = pozerFac.getPozerTopToTop();
//      ByteObject rightToRight = pozerFac.getPozerRightToRight();
//
//      ByteObject topToMid = pozerFac.getPozerTopToMid();
//      ByteObject botToMid = pozerFac.getPozerBotToMid();
//      ByteObject endToMid = botToMid;
//
//      JComponentLayouter white = new JComponentLayouter(slc);
//      white.setName("white");
//      white.setBackground(Color.white);
//      white.setSizer(sizer30PercentParent, sizer40PercentParent);
//      white.setPozer(centerToCenter, centerToCenter);
//
//      
//      JComponentLayouter green = new JComponentLayouter(slc);
//      green.setName("green");
//      green.setBackground(Color.GREEN);
//      green.setSizer(sizer30PercentParent, sizer40PercentParent);
//      green.setPozer(leftToLeft, topToTop);
//
//      JComponentLayouter yellow = new JComponentLayouter(slc);
//      yellow.setName("yellow");
//      yellow.setBackground(Color.yellow);
//      yellow.setSizer(sizer30PercentParent, sizer10PercentParent);
//      yellow.setPozer(leftToLeft, botToBot);
//
//      
//      JComponentLayouter red = new JComponentLayouter(slc);
//      red.setName("red");
//      red.setBackground(Color.RED);
//      red.setSizer(sizer20PercentParent, sizer10PercentParent);
//      red.setPozer(rightToRight, botToBot);
//      
//      
//      JComponentLayouter blue = new JComponentLayouter(slc);
//      blue.setName("blue");
//      blue.setBackground(Color.BLUE);
//      blue.setSizer(sizer30PercentParent, sizer10PercentParent);
//      blue.setPozer(rightToRight, topToTop);
//      
//      JComponentLayouter grey = new JComponentLayouter(slc);
//      grey.setName("grey");
//      grey.setBackground(Color.gray);
//      grey.setSizer(sizer10PercentParent, sizer10PercentParent);
//      grey.setPozer(centerToCenter, centerToCenter);
//    
//      
//      JComponentLayouter pink = new JComponentLayouter(slc);
//      pink.setName("pink");
//      pink.setBackground(Color.pink);
//      pink.setSizer(sizer10PercentParent, sizer40PercentParent);
//      pink.setPozer(centerToCenter, botToBot);
//    
//      JComponentLayouter cyan = new JComponentLayouter(slc);
//      cyan.setName("cyan");
//      cyan.setBackground(Color.CYAN);
//      cyan.setSizer(sizer45PercentParent, sizer20PercentParent);
//      cyan.setPozer(rightToRight, centerToCenter);
//      
//      JComponentLayouter orange = new JComponentLayouter(slc);
//      orange.setName("orange");
//      orange.setBackground(Color.orange);
//      orange.setSizer(sizer10PercentParent, sizer20PercentParent);
//      orange.setPozer(topToMid, botToMid);
//    
//      JComponentLayouter magenta = new JComponentLayouter(slc);
//      magenta.setName("magenta");
//      magenta.setBackground(Color.magenta);
//      magenta.setSizer(sizer20PercentParent, sizer20PercentParent);
//      magenta.setPozer(endToMid, topToMid);
//    
//      
//      
//      slc.toStringSetDebugBreaks(new LayoutWillListenerAdapter() {
//
//         public void layoutWillComputeSizeW(ILayoutable layoutable) {
//         }
//
//         public void layoutWillComputeSizeH(ILayoutable layoutable) {
//
//         }
//
//         public void layoutWillComputeSizes(ILayoutable layoutable) {
//            if (layoutable == green) {
//               isBreak = true;
//            }
//         }
//
//         public void layoutWillComputePositionY(ILayoutable layoutable) {
//
//         }
//
//         public void layoutWillComputePositionX(ILayoutable layoutable) {
//            if (layoutable == grey.getLayoutableAdapter()) {
//               isBreak = true;
//            }
//         }
//
//         public void layoutWillComputePositions(ILayoutable layoutable) {
//            if (layoutable == green) {
//               isBreak = true;
//            }
//         }
//      });
//
//      JFrameLayouter frame = new JFrameLayouter(slc);
//
//      JPanelLayouter panel = frame.getPanelLayouter();
//      //order must not be important
//      panel.addLayoutableOnTop(white);
//      panel.addLayoutableOnTop(green);
//      panel.addLayoutableOnTop(red);
//      panel.addLayoutableOnTop(blue);
//      panel.addLayoutableOnTop(yellow);
//      panel.addLayoutableOnTop(grey);
//      panel.addLayoutableOnTop(cyan);
//      panel.addLayoutableOnTop(pink);
//      panel.addLayoutableOnTop(orange);
//      panel.addLayoutableOnTop(magenta);
//      
//      //panel will have its size once packed
//      frame.showDefClosePackLocationNull();
//
//      
//      //#debug
//      slc.toDLog().pAlways("msg", frame, SwingLayouterDemoRunSimpleGreen.class, "main", IStringable.LVL_05_FINE, false);
//   }
//
//}
