package cyclone.repo;


import org.kohsuke.github.*;
import org.kohsuke.github.extras.OkHttpConnector;

import java.io.*;
import java.util.List;
import java.util.Set;

/**
 * Created by DilshaniS on 25/08/2015.
 */
public class repoConnection {

    public static void main(String args[]) throws IOException {

        try {

            String content = "login=dilshani\n" +
                    "password=Mylady@123\n";

            File file = new File("C:/Users/DilshaniS/.github");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.close();

            System.out.println("--------------Done------------------");

            GitHub github = GitHub.connect();

            GHRepository repo =github.getRepository("Dilshani/cyclone");

            GHUser user;
            org.kohsuke.github.GHCommit.File files;
            List<GHCommit> ghCommits=repo.listCommits().asList();
            for(int i=0;i<ghCommits.size();i++) {
                user = ghCommits.get(i).getCommitter();
                System.out.println(user.toString());
                System.out.println(user.getCreatedAt());
                List<org.kohsuke.github.GHCommit.File> fileList=ghCommits.get(i).getFiles();
                for(int j=0;j<fileList.size();j++) {
                    System.out.println(fileList.get(j).getPatch());
                }

                // System.out.println(files.getPatch());
           /* System.out.println(ghCommitStates.get(i).getCreatedAt());
            for(int j=0;j<ghCommitStates.size();j++) {
                System.out.println(ghCommitStates.get(j).getCreatedAt());
            }*/
            }

            int n = repo.getForks();
            System.out.println(n);

            int count = repo.listCommits().asList().size();
            System.out.println(count);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

