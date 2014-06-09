package slideshowdata;

import graphicshandler.ShapePoint;
import graphicshandler.SlideShapeFactory;
import imagehandler.SlideImage;

import java.util.ArrayList;
import java.util.PriorityQueue;

import javafx.scene.shape.Shape;
import smarttrolleygui.slideshow.Slide;
import texthandler.SlideText;
import texthandler.SlideTextBody;
import videohandler.SlideVideo;
import audiohandler.AudioHandler;

public class SlideDataImporter {
	static SlideShowData data;
	static ArrayList<Slide> mediaSlideList;

	public SlideDataImporter(SlideShowData data) {

	}

	private static ArrayList<Slide> importSlides() {
		ArrayList<Slide> slides = new ArrayList<Slide>();

		for (SlideData slide : data.getSlides()) {
			slides.add(importSlide(slide));
		}

		return slides;
	}

	public static Slide importSlide(SlideData slideData) {

		Slide slide = null;

		if (slideData != null) {
			ArrayList<SlideImage> images = importImages(slideData.getImages());
			ArrayList<SlideVideo> videos = importVideos(slideData.getVideos());
			ArrayList<Shape> shapes = importShapes(slideData.getShapes());
			ArrayList<SlideText> texts = importTexts(slideData.getTexts());
			ArrayList<AudioHandler> audios = importAudios(slideData.getAudios());

			// TODO the 0.83 & 1.1 should be replaced with SmartTrolleyGUI.MIN_WINDOW_WIDTH/data.getDocumentinfo().getWidth() &
			// SmartTrolleyGUI.MIN_WINDOW_HEIGHT/data.getDocumentinfo().getHeight()
			slide = new Slide(0.83, 1.1, shapes, images, audios, texts, videos, slideData.getDuration());
		} else {
			slide = new Slide(0, 0, null, null, null, null, null, 0);
		}

		if (data != null && data.getDefaults() != null) {
			slide.setStyle("background-color:" + data.getDefaults().getBackgroundcolor());
		}

		return slide;
	}

	private static ArrayList<AudioHandler> importAudios(ArrayList<AudioData> audioDatas) {

		int defaultVolume = 1;
		ArrayList<AudioHandler> audios = new ArrayList<AudioHandler>();

		if (audioDatas != null) {
			for (AudioData audioData : audioDatas) {
				audios.add(new AudioHandler(audioData.getUrlname(), audioData.getStarttime(), defaultVolume));
			}
		}

		return audios;
	}

	private static ArrayList<SlideText> importTexts(ArrayList<TextData> textDatas) {

		ArrayList<SlideText> texts = new ArrayList<SlideText>();
		ArrayList<SlideTextBody> bodies = new ArrayList<SlideTextBody>();

		
		if (textDatas != null){
		for(TextData textData : textDatas){
			
			bodies = importTextBodies(textData.getTextbodies());
			texts. add(new SlideText(
										bodies, 
										textData.getFont(), 
										textData.getFontcolor(), 
										textData.getFontsize(), 
										textData.getXstart(),
										textData.getYstart(), 
										textData.getXend(), 
										textData.getYend(), 
										textData.getStarttime(), 
										textData.getDuration(),
										textData.getLayer())
			);
		}}
		
		return texts;
	}

	private static ArrayList<SlideTextBody> importTextBodies(ArrayList<TextBodyData> bodyDatas) {

		ArrayList<SlideTextBody> bodies = new ArrayList<SlideTextBody>();


		if (bodyDatas != null){
		for(TextBodyData body : bodyDatas){
			
			bodies.add(new SlideTextBody(body.getTextstring(), 
										 body.getBold(), 
										 body.getItalic(), 
										 body.getUnderlined(),
										 body.getBranch()));
			
		}}
		return bodies;
	}

	private static ArrayList<Shape> importShapes(ArrayList<ShapeData> shapeDatas) {

		ArrayList<Shape> shapes = new ArrayList<Shape>();
		SlideShapeFactory shapeFactory;
		PriorityQueue<ShapePoint> points;

		
		if (shapeDatas != null){
		for(ShapeData shapeData : shapeDatas){
			points = importPoints(shapeData.getPoints());
			
			shapeFactory = new SlideShapeFactory(
													points,
													shapeData.getWidth(), 
													shapeData.getHeight(), 
													shapeData.getFillcolor(), 
													shapeData.getLinecolor(), 
													shapeData.getStarttime(), 
													shapeData.getDuration(),
													shapeData.getBranch(),
													shapeData.getLayer());
			shapes.add(shapeFactory.getShape());
		}}
		
		return shapes;
	}

	private static PriorityQueue<ShapePoint> importPoints(ArrayList<PointData> pointDatas) {

		PriorityQueue<ShapePoint> points = new PriorityQueue<ShapePoint>();

		for (PointData pointData : pointDatas) {
			points.add(new ShapePoint(pointData.getX(), pointData.getY(), pointData.getNum()));
		}

		return points;
	}

	private static ArrayList<SlideVideo> importVideos(ArrayList<VideoData> videoDatas) {

		ArrayList<SlideVideo> videos = new ArrayList<SlideVideo>();
		
		if (videoDatas != null){
		for(VideoData videoData : videoDatas){
			
			videos.add(new SlideVideo(videoData.getUrlname(),
									  videoData.getXstart(), 
									  videoData.getYstart(), 
									  videoData.getWidth(), 
									  videoData.getHeight(), 
									  videoData.getLoop(), 
									  videoData.getStarttime(),
									  videoData.getDuration(),
									  videoData.getLayer()));
		}}
		return videos;
	}

	private static ArrayList<SlideImage> importImages(ArrayList<ImageData> imageDatas) {

		ArrayList<SlideImage> images = new ArrayList<SlideImage>();
		
		if (imageDatas != null){
		for(ImageData imageData : imageDatas){
			
			images.add(new SlideImage(imageData.getUrlname(),
									  imageData.getXstart(), 
									  imageData.getYstart(), 
									  imageData.getWidth(), 
									  imageData.getHeight(), 
									  imageData.getStarttime(),
									  imageData.getDuration(),
									  imageData.getBranch(),
									  imageData.getLayer()));
		}}
		
		return images;
	}

	public static ArrayList<Slide> getSlides(SlideShowData showData) {

		SlideDataImporter.data = showData;

		return importSlides();
	}
}
