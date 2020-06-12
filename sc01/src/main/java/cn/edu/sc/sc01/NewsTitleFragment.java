package cn.edu.sc.sc01;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.edu.sc.sc01.db.NewsDAO;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewsTitleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewsTitleFragment extends Fragment {
    private boolean isTwoPane;
    private RecyclerView newsTitleRecyclerView;
    private NewsAdapter adapter;
    private ArrayList<News> arrayList;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewsTitleFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewsTitleFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewsTitleFragment newInstance(String param1, String param2) {
        NewsTitleFragment fragment = new NewsTitleFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if(getActivity().findViewById(R.id.news_content_layout)!=null){
            isTwoPane=true;
        }else{
            isTwoPane=false;
        }

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_news_title, container, false);
        View view= inflater.inflate(R.layout.fragment_news_title, container, false);
        newsTitleRecyclerView=view.findViewById(R.id.news_title_recycler_view);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        newsTitleRecyclerView.setLayoutManager(layoutManager);
        arrayList=getNews();
        adapter=new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(adapter);
        return view;
    }
    private ArrayList<News> getNews(){
        ArrayList<News> newsList=new ArrayList<>();
        NewsDAO dao=new NewsDAO(getActivity());
        newsList=dao.getAllNews();
//        for(int i=1;i<=50;i++){
//            News news=new News();
//            news.setTitle("This is a new title"+i);
//            news.setContent(getRandomLengthContent("This is news content"+i+"."));
//            newsList.add(news);
//        }
        return newsList;
    }
    public void refresh(){
        arrayList=getNews();
        adapter=new NewsAdapter(getNews());
        newsTitleRecyclerView.setAdapter(adapter);
    }
//    private String getRandomLengthContent(String content){
//        Random random=new Random();
//        int length=random.nextInt(20)+1;
//        StringBuilder builder=new StringBuilder();
//        for(int i=0;i<length;i++){
//            builder.append(content);
//        }
//        return builder.toString();
//    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
        private List<News> mNewsList;

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item,parent,false);
            final ViewHolder holder=new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    News news=mNewsList.get(holder.getAdapterPosition());
                    System.out.println("------------isTwoPane---"+isTwoPane);
                    if(isTwoPane){
                        NewsContentFragment rightFragment=(NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        rightFragment.refresh(news.getTitle(),news.getContent());
                    }else{
                        Intent intent=new Intent(getActivity(),NewsContentActivity.class);
                        intent.putExtra("news_title",news.getTitle());
                        intent.putExtra("news_content",news.getContent());
                        startActivity(intent);

                    }
                }
            });

            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            News news=mNewsList.get(position);
            holder.newsTitleText.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return mNewsList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            TextView newsTitleText;
            public ViewHolder(View view){
                super(view);
                newsTitleText=view.findViewById(R.id.news_title);
            }
        }
        public NewsAdapter(List<News> newsList){
            mNewsList=newsList;
        }

    }
}